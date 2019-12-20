package com.sensesnet.command.impl;

import com.sensesnet.ServiceProvider;
import com.sensesnet.command.ICommand;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.dto.UserDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.implementation.UserService;
import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.authentication.UserInfo;
import com.sensesnet.pojo.authentication.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.sensesnet.util.HttpRequestValidator.isPost;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Command : Sign Up
 */
public class SignUpCommand implements ICommand
{
    private static final Logger log = LogManager.getLogger(SignUpCommand.class);
    private UserService userService = ServiceProvider.getInstance().getUserService();
    private UserDto userDto;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (!isPost(request)) {
            request
                    .getRequestDispatcher(ConstantProvider.getPagePath().SIGN_UP_PAGE)
                    .forward(request, response);
            return;
        }
        String firstName = request
                .getParameter(ConstantProvider.getRequestParameter().FIRST_NAME);
        String secondName = request
                .getParameter(ConstantProvider.getRequestParameter().SECOND_NAME);
        String birthday = request
                .getParameter(ConstantProvider.getRequestParameter().BIRTHDAY);
        String address = request
                .getParameter(ConstantProvider.getRequestParameter().ADDRESS);
        String phone = request
                .getParameter(ConstantProvider.getRequestParameter().PHONE);
        String login = request
                .getParameter(ConstantProvider.getRequestParameter().LOGIN);
        String password = request
                .getParameter(ConstantProvider.getRequestParameter().PASSWORD);
        String confirmPassword = request
                .getParameter(ConstantProvider.getRequestParameter().CONFIRM_PASSWORD);

        try
        {
            User user = userService.getUserByLogin(login);
            if (user != null)
            {
                response.sendRedirect("Controller?action=sign_up&errorMessage=Account is found, use other email.");
                return;
            }
            HttpSession session = request.getSession(true);
            userService.addEntity(
                    new UserDto(
                            User.builder()
                                    .userLogin(login)
                                    .userPassword(password)
                                    .userRole(1)
                                    .build(),
                            UserInfo.builder()
                                    .userName(firstName)
                                    .userSurname(secondName)
                                    .userBirthday(birthday)
                                    .userPhone(phone)
                                    .userAddress(address)
                                    .build(),
                            UserRole.builder()
                                    .roleId(1)
                                    .roleName("USER").build()));
            session.setAttribute("currentUser", userService.getUserByLogin(login));
            session.setAttribute("errorMessage", "Account has been registered successfully");
            request.getRequestDispatcher(ConstantProvider.getPagePath().HOME_ADMIN_PAGE).forward(request, response);
//            response.sendRedirect("Controller?action=home&errorMessage=Account has been registered successfully. Try to sign in...");
        }
        catch (SecurityException | ServiceException e)
        {
            log.warn("[LoginFilter] Security exception: lost connection to DB.");
            response.sendRedirect("Controller?action=sign_up&errorMessage=Lost connection to DB. try later...");
        }
//        RequestDispatcher dispatcher = request
//                .getRequestDispatcher(ConstantProvider.getPagePath().SIGN_UP_PAGE);
//        dispatcher.forward(request, response);
    }
}
