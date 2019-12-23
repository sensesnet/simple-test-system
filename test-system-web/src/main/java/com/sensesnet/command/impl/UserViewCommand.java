package com.sensesnet.command.impl;

import com.sensesnet.IUserRoleService;
import com.sensesnet.IUserService;
import com.sensesnet.ServiceFactory;
import com.sensesnet.command.ICommand;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.dto.UserDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.impl.UserService;
import com.sensesnet.pojo.authentication.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * UserViewCommand: Show all users
 */
public class UserViewCommand implements ICommand
{
    private static final Logger log = LogManager.getLogger(UserViewCommand.class);
    private IUserService userService = ServiceFactory.getInstance().getUserService();
    private IUserRoleService userRoleService = ServiceFactory.getInstance().getUserRoleService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(ConstantProvider.getPagePath().USER_LIST_PAGE);
        List<UserDto> userList = new LinkedList<>();
        for (User user : userService.getListOfEntity())
        {
            userList.add(new UserDto(user, userRoleService.getRoleById(user.getUserRole())));
        }
        if (userList.size() == 0)
        {
            response.sendRedirect("Controller?action=home&errorMessage=Have no found any accounts at test systems.");
            return;
        }
        request.setAttribute("userList", userList);
        dispatcher.forward(request, response);
        log.info("[UserViewCommand] User List has been forward.");
    }
}
