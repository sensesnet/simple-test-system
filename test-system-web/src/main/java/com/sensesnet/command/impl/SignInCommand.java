package com.sensesnet.command.impl;

import com.sensesnet.ServiceProvider;
import com.sensesnet.command.ICommand;
import com.sensesnet.implementation.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Command : Sign In
 */
public class SignInCommand implements ICommand
{
    UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("signIn.jsp");
        dispatcher.forward(request, response);
    }
}
