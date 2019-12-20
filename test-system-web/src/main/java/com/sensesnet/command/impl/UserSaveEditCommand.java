package com.sensesnet.command.impl;

import com.sensesnet.ServiceProvider;
import com.sensesnet.command.ICommand;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.constant.DateFormat;
import com.sensesnet.dto.UserDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.implementation.UserInfoService;
import com.sensesnet.implementation.UserService;
import com.sensesnet.util.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class UserSaveEditCommand implements ICommand
{
    private static final Logger log = LogManager.getLogger(UserSaveEditCommand.class);
    private UserService userService = ServiceProvider.getInstance().getUserService();
    private UserInfoService userInfoService = ServiceProvider.getInstance().getUserInfoService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {

        UserDto userData;
        UserDto updatedUserData;
        userData = userService
                .getByIdentifier(
                        Integer.valueOf(request.getParameter("userId")));
        userData.getUserInfo()
                .setUserName(request.getParameter("firstName"));
        userData.getUserInfo()
                .setUserSurname(request.getParameter("secondName"));
        userData.getUserInfo()
                .setUserAddress(request.getParameter("address"));
        userData.getUserInfo()
                .setUserBirthday(request.getParameter("birthday"));
        userData.getUserInfo().setUserPhone(request.getParameter("phone"));
        userData.getUserRole().setRoleName(request.getParameter("role"));
        userService.editEntity(userData);
        userInfoService.editEntity(userData.getUserInfo());
        updatedUserData = userService
                .getByIdentifier(
                        Integer.valueOf(request.getParameter("userId")));
        if (userData.equals(updatedUserData))
            response.sendRedirect("Controller?action=user_view&errorMessage=New details "
                    + "has been updated successfully.");
        else
            response.sendRedirect("Controller?action=user_view&errorMessage=New details "
                    + "has NOT been updated. Try again ...");
    }
}
