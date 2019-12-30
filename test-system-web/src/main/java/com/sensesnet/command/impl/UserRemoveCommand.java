package com.sensesnet.command.impl;

import com.sensesnet.UserService;
import com.sensesnet.ServiceFactory;
import com.sensesnet.command.Command;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Command: Remove user
 */
public class UserRemoveCommand implements Command
{
    private static final Logger log = LogManager.getLogger(UserRemoveCommand.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServiceException
    {
        User user;
        userService.removeUser(
                userService.getUserById(Integer.valueOf(request.getParameter("userId"))));
        user = userService.getUserById(Integer.valueOf(request.getParameter("userId")));
        if (user == null)
            response.sendRedirect("Controller?action=user_view&errorMessage=User "
                    + "has been removed successfully.");
        else
            response.sendRedirect("Controller?action=user_view&errorMessage=User "
                    + "has NOT been removed. Try again ...");
    }
}
