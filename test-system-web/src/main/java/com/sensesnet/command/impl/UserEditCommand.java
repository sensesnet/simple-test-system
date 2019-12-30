package com.sensesnet.command.impl;

import com.sensesnet.UserRoleService;
import com.sensesnet.UserService;
import com.sensesnet.ServiceFactory;
import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.dto.UserDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
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
public class UserEditCommand implements Command
{
    private static final Logger log = LogManager.getLogger(UserEditCommand.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private UserRoleService userRoleService = ServiceFactory.getInstance().getUserRoleService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {
        User user = userService.getUserById(Integer.valueOf(request.getParameter("userId")));
        RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantProvider.getPagePath().USER_EDIT_PAGE);
        request.setAttribute("editUser",
                new UserDto(user, userRoleService.getRoleById(user.getUserRole())));
        dispatcher.forward(request, response);
    }
}
