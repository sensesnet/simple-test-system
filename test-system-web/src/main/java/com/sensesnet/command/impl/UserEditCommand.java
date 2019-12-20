package com.sensesnet.command.impl;

import com.sensesnet.ServiceProvider;
import com.sensesnet.command.ICommand;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.exception.ServiceException;
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
public class UserEditCommand implements ICommand
{
    private static final Logger log = LogManager.getLogger(UserEditCommand.class);
    private UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantProvider.getPagePath().USER_EDIT_PAGE);
        request.setAttribute("editUser",
                userService.getByIdentifier(Integer.valueOf(request.getParameter("userId"))));
        dispatcher.forward(request, response);
    }
}
