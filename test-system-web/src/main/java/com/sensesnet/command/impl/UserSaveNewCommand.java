package com.sensesnet.command.impl;

import com.sensesnet.UserRoleService;
import com.sensesnet.UserService;
import com.sensesnet.ServiceFactory;
import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.impl.UserRoleServiceImpl;
import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.authentication.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class UserSaveNewCommand implements Command
{
    private static final Logger log = LogManager.getLogger(UserSaveNewCommand.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private UserRoleService userRoleService = ServiceFactory.getInstance().getUserRoleService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {
        User user;
        UserRole role;

        String firstName = request.getParameter(ConstantProvider.getRequestParameter().FIRST_NAME);
        String secondName = request.getParameter(ConstantProvider.getRequestParameter().SECOND_NAME);
        String birthday = request.getParameter(ConstantProvider.getRequestParameter().BIRTHDAY);
        String address = request.getParameter(ConstantProvider.getRequestParameter().ADDRESS);
        String phone = request.getParameter(ConstantProvider.getRequestParameter().PHONE);
        String login = request.getParameter(ConstantProvider.getRequestParameter().LOGIN);
        String password = request.getParameter(ConstantProvider.getRequestParameter().PASSWORD);
        String confurmPassword = request.getParameter(ConstantProvider.getRequestParameter().CONFIRM_PASSWORD);
        String roleName = request.getParameter(ConstantProvider.getRequestParameter().ROLE);

        user = userService.getUserByEmail(login);
        if (user != null)
        {
            response.sendRedirect("Controller?action=user_add&errorMessage=Account is found, use other email.");
            return;
        }
        role = userRoleService.getRoleByName(roleName);
        userService.createUser(
                        User.builder()
                                .userLogin(login)
                                .userPassword(password)
                                .userRole(role.getRoleId())
                                .userName(firstName)
                                .userSurname(secondName)
                                .userAddress(address)
                                .userBirthday(birthday)
                                .userPhone(phone)
                                .build());
        user = userService.getUserByEmail(login);
        if (user != null)
            response.sendRedirect("Controller?action=user_view&message=Account has been registered successfully.");
        else
        {
            log.error("[UserSaveNewCommand] Security exception: lost connection to DB.");
            response.sendRedirect("Controller?action=user_view&errorMessage=Lost connection to DB. try later...");
        }
    }
}

