package com.sensesnet.command.impl;

import com.sensesnet.IUserService;
import com.sensesnet.ServiceFactory;
import com.sensesnet.command.ICommand;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.impl.UserService;
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
    private IUserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantProvider.getPagePath().USER_EDIT_PAGE);
        request.setAttribute("editUser",
                userService.getUserById(Integer.valueOf(request.getParameter("userId"))));
        dispatcher.forward(request, response);
    }
}
