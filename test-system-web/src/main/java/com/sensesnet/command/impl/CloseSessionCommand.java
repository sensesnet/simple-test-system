package com.sensesnet.command.impl;


import com.sensesnet.command.ICommand;
import com.sensesnet.constant.ConstantProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Sign out
 */
public class CloseSessionCommand implements ICommand
{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("currentUser",null);
        request.setAttribute("userRole",null);
//        request.getSession().invalidate();
        response.sendRedirect(ConstantProvider.getPagePath().INDEX_PAGE);
    }
}
