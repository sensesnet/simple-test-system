package com.sensesnet.servlet;

import com.sensesnet.ServiceProvider;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.implementation.UserRoleService;
import com.sensesnet.implementation.UserService;
import com.sensesnet.pojo.authentication.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Login Filter servlet
 */
public class LoginFilter extends HttpServlet
{
    private static final Logger log = LogManager.getLogger(LoginFilter.class);
    private UserService userService = ServiceProvider.getInstance().getUserService();
    private UserRoleService userRoleService = ServiceProvider.getInstance().getUserRoleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        User user;
        HttpSession session;
        String login = request.getParameter(ConstantProvider.getRequestParameter().LOGIN);
        String password = request.getParameter(ConstantProvider.getRequestParameter().PASSWORD);

        try
        {
            user = userService.authorization(login, password);
            if (user == null)
            {
                response.sendRedirect("Controller?action=sign_in&errorMessage=Account is found, use other email.");
                return;
            }
            session = request.getSession(true);
            session.setAttribute("currentUser", user);
            response.sendRedirect("Controller?action=home");
        }
        catch (SecurityException e)
        {
            log.warn("[LoginFilter] Security exception: lost connection to DB.");
            response.sendRedirect("Controller?action=sign_in&errorMessage=Lost connection to DB. try later...");
        }

    }
}
