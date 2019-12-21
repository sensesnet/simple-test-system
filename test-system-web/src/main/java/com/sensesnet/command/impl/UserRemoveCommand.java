package com.sensesnet.command.impl;

import com.sensesnet.ServiceProvider;
import com.sensesnet.command.ICommand;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.dto.UserDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.implementation.UserInfoService;
import com.sensesnet.implementation.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
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
public class UserRemoveCommand implements ICommand
{
    private static final Logger log = LogManager.getLogger(UserRemoveCommand.class);
    private UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {
        UserDto userDto;
        userService.removeEntity(
                userService.getByIdentifier(Integer.valueOf(request.getParameter("userId"))));
        userDto = userService.getByIdentifier(Integer.valueOf(request.getParameter("userId")));
        if (userDto == null)
            response.sendRedirect("Controller?action=user_view&errorMessage=User "
                    + "has been removed successfully.");
        else
            response.sendRedirect("Controller?action=user_view&errorMessage=User "
                    + "has NOT been removed. Try again ...");
    }
}