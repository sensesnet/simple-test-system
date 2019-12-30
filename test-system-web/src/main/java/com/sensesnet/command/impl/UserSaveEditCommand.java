package com.sensesnet.command.impl;

import com.sensesnet.UserRoleService;
import com.sensesnet.UserService;
import com.sensesnet.ServiceFactory;
import com.sensesnet.command.Command;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
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
public class UserSaveEditCommand implements Command
{
    private static final Logger log = LogManager.getLogger(UserSaveEditCommand.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private UserRoleService userRoleService = ServiceFactory.getInstance().getUserRoleService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {

        User userData;
        User updatedUserData;
        userData = userService
                .getUserById(Integer.valueOf(request.getParameter("userId")));
        userData.setUserName(request.getParameter("firstName"));
        userData.setUserSurname(request.getParameter("secondName"));
        userData.setUserAddress(request.getParameter("address"));
        userData.setUserBirthday(request.getParameter("birthday"));
        userData.setUserPhone(request.getParameter("phone"));
        userData.setUserRole(userRoleService.getRoleByName(request.getParameter("role")).getRoleId());
        userService.updateUser(userData);
        updatedUserData = userService
                .getUserById(Integer.valueOf(request.getParameter("userId")));
        if (userData.equals(updatedUserData))
            response.sendRedirect("Controller?action=user_view&errorMessage=New details "
                    + "has been updated successfully.");
        else
            response.sendRedirect("Controller?action=user_view&errorMessage=New details "
                    + "has NOT been updated. Try again ...");
    }
}
