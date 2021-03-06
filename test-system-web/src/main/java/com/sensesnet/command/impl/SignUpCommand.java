package com.sensesnet.command.impl;

import com.sensesnet.UserService;
import com.sensesnet.ServiceFactory;
import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.dto.UserDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
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
public class SignUpCommand implements Command
{
    private static final Logger log = LogManager.getLogger(SignUpCommand.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private UserDto userDto;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (!isPost(request))
        {
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
            User user = userService.getUserByEmail(login);
            if (user != null)
            {
                response.sendRedirect("Controller?action=sign_up&errorMessage=Account is found, use other email.");
                return;
            }
            userService.createUser(
                            User.builder()
                                    .userLogin(login)
                                    .userPassword(password)
                                    .userRole(1)
                                    .userName(firstName)
                                    .userSurname(secondName)
                                    .userBirthday(birthday)
                                    .userPhone(phone)
                                    .userAddress(address)
                                    .build());

            User currentUser = userService.getUserByEmail(login);
            if (currentUser == null)
            {
                response.sendRedirect("Controller?action=sign_in&errorMessage=Account is found, try to sign up.");
                return;
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("currentUser", currentUser);
            response.sendRedirect("Controller?action=home&message=Account has been registered successfully");
        }
        catch (ServiceException e)
        {
            log.warn("[LoginFilter] Security exception: lost connection to DB.");
            response.sendRedirect("Controller?action=sign_up&errorMessage=Lot connectsion to DB. try later...");
        }
    }
}
