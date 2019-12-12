package com.sensesnet.command.impl;

import com.sensesnet.ServiceProvider;
import com.sensesnet.command.ICommand;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.implementation.UserRoleService;
import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.authentication.UserRole;
import com.sensesnet.servlet.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Home page
 * - get User role
 * - add param to session
 * - return redirect to depended page by role ('Admin' or 'User').
 */
public class HomeCommand implements ICommand
{
    private static final Logger log = LogManager.getLogger(HomeCommand.class);
    private UserRoleService userRoleService = ServiceProvider.getInstance().getUserRoleService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user;
        UserRole userRole;
        RequestDispatcher dispatcher;

        user = (User) request.getSession().getAttribute("currentUser");
        try
        {
            userRole = userRoleService.getByIdentifier(user.getUserRole());
            request.getSession().setAttribute("userRole", userRoleService.getByIdentifier(user.getUserRole()));

            if (userRole.getRoleName().equalsIgnoreCase("Admin"))
            {
                dispatcher = request.getRequestDispatcher(ConstantProvider.getPagePath().HOME_ADMIN_PAGE);
                dispatcher.forward(request, response);
            }

            if (userRole.getRoleName().equalsIgnoreCase("User"))
            {
                dispatcher = request.getRequestDispatcher(ConstantProvider.getPagePath().HOME_USER_PAGE);
                dispatcher.forward(request, response);
            }
        }
        catch (ServiceException e)
        {
            log.warn("[HomeCommand] Security exception: lost connection to DB.");
            response.sendRedirect("Controller?action=sign_in&errorMessage=Lost connection to DB. try later...");
        }
    }
}
