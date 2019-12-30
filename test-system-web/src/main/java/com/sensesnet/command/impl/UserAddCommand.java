package com.sensesnet.command.impl;

import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;

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
public class UserAddCommand implements Command
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(ConstantProvider.getPagePath().USER_ADD_PAGE);
        dispatcher.forward(request, response);
    }
}
