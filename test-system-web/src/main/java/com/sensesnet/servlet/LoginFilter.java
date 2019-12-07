package com.sensesnet.servlet;

import com.sensesnet.exception.ServiceException;
import com.sensesnet.implementation.UserService;
import com.sensesnet.pojo.authentication.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class LoginFilter extends HttpServlet
{
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = new UserService();


        User user = null;
        if (login != null)
        {
            user = userService.authorization(login, password);
        }
        else
        {
            response.sendRedirect("reIndex.jsp");
        }
        if (user != null)
        {
            request.getSession().setAttribute("currentUser", user);
            response.sendRedirect("Controller");
        }
        else response.sendRedirect("reIndex.jsp");

    }
}
