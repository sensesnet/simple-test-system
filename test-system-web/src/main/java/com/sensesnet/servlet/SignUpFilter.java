package com.sensesnet.servlet;

import com.sensesnet.ServiceProvider;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.dto.UserDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.implementation.UserInfoService;
import com.sensesnet.implementation.UserRoleService;
import com.sensesnet.implementation.UserService;
import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.authentication.UserInfo;
import com.sensesnet.pojo.authentication.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * SignUpFilter: Filter date throught 'Sign Up' form
 */
public class SignUpFilter extends HttpServlet
{
    private static final Logger log = LogManager.getLogger(SignUpFilter.class);
    private UserService userService = ServiceProvider.getInstance().getUserService();
    private UserRoleService userRoleService = ServiceProvider.getInstance().getUserRoleService();
    private UserInfoService userInfoService = ServiceProvider.getInstance().getUserInfoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
//        User user;
//        UserInfo userInfo;
//        HttpSession session;
//        String firstName = request.getParameter(ConstantProvider.getRequestParameter().FIRST_NAME);
//        String secondName = request.getParameter(ConstantProvider.getRequestParameter().SECOND_NAME);
//        String birthday = request.getParameter(ConstantProvider.getRequestParameter().BIRTHDAY);
//        String address = request.getParameter(ConstantProvider.getRequestParameter().ADDRESS);
//        String phone = request.getParameter(ConstantProvider.getRequestParameter().PHONE);
//        String login = request.getParameter(ConstantProvider.getRequestParameter().LOGIN);
//        String password = request.getParameter(ConstantProvider.getRequestParameter().PASSWORD);
//
//        try
//        {
//            user = userService.getUserByLogin(login);
//            if (user != null)
//            {
//                response.sendRedirect("Controller?action=sign_up&errorMessage=Account is found, use other email.");
//                return;
//            }
//            session = request.getSession(true);
//            userService.addEntity(
//                    new UserDto(
//                            User.builder()
//                                    .userLogin(login)
//                                    .userPassword(password)
//                                    .userRole(1)
//                                    .build(),
//                            UserInfo.builder()
//                                    .userName(firstName)
//                                    .userSurname(secondName)
//                                    .userBirthday(birthday)
//                                    .userPhone(phone)
//                                    .userAddress(address)
//                                    .build(),
//                            UserRole.builder()
//                                    .roleId(1)
//                                    .roleName("USER").build()));
//            response.sendRedirect("Controller?action=sign_up&errorMessage=Account has been registered successfully. Try to sign in...");
//            Thread.sleep(2000);
//            session.setAttribute("currentUser", userService.getUserByLogin(login));
//            response.sendRedirect("Controller?action=home");
//        }
//        catch (SecurityException | ServiceException | InterruptedException e)
//        {
//            log.warn("[LoginFilter] Security exception: lost connection to DB.");
//            response.sendRedirect("Controller?action=sign_up&errorMessage=Lost connection to DB. try later...");
//        }
    }
}
