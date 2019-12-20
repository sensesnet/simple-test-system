package com.sensesnet.command.impl;

import com.sensesnet.ServiceProvider;
import com.sensesnet.command.ICommand;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class UserSaveNewCommand implements ICommand
{
    private static final Logger log = LogManager.getLogger(UserSaveNewCommand.class);
    private UserService userService = ServiceProvider.getInstance().getUserService();
    private UserInfoService userInfoService = ServiceProvider.getInstance().getUserInfoService();
    private UserRoleService userRoleService = ServiceProvider.getInstance().getUserRoleService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {
        User user;
        UserInfo userInfo;
        UserRole role;
        HttpSession session;

        String firstName = request.getParameter(ConstantProvider.getRequestParameter().FIRST_NAME);
        String secondName = request.getParameter(ConstantProvider.getRequestParameter().SECOND_NAME);
        String birthday = request.getParameter(ConstantProvider.getRequestParameter().BIRTHDAY);
        String address = request.getParameter(ConstantProvider.getRequestParameter().ADDRESS);
        String phone = request.getParameter(ConstantProvider.getRequestParameter().PHONE);
        String login = request.getParameter(ConstantProvider.getRequestParameter().LOGIN);
        String password = request.getParameter(ConstantProvider.getRequestParameter().PASSWORD);
        String confurmPassword = request.getParameter(ConstantProvider.getRequestParameter().CONFIRM_PASSWORD);
        String roleName = request.getParameter(ConstantProvider.getRequestParameter().ROLE);

        user = userService.getUserByLogin(login);
        if (user != null)
        {
            response.sendRedirect("Controller?action=user_add&errorMessage=Account is found, use other email.");
            return;
        }
        role = userRoleService.getByName(roleName);
        userService.addEntity(
                new UserDto(
                        User.builder()
                                .userLogin(login)
                                .userPassword(password)
                                .build(),
                        UserInfo.builder()
                                .userName(firstName)
                                .userSurname(secondName)
                                .userBirthday(birthday)
                                .userPhone(phone)
                                .userAddress(address)
                                .build(),
                        role));

        user = userService.getUserByLogin(login);
        if (user != null)
            response.sendRedirect("Controller?action=user_view&errorMessage=Account has been registered successfully.");
        else
        {
            log.warn("[LoginFilter] Security exception: lost connection to DB.");
            response.sendRedirect("Controller?action=user_view&errorMessage=Lost connection to DB. try later...");
        }
    }
}

