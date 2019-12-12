package com.sensesnet.servlet;

import com.sensesnet.command.CommandProvider;
import com.sensesnet.command.ICommand;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Application controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet
{
    private static final Logger log = LogManager.getLogger(Controller.class);
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    public void init() throws ServletException
    {
        log.info("[Controller] Init Controller servlet.");
        super.init();
    }

    @Override
    public void destroy()
    {
        log.info("[Controller] Destroy Controller servlet.");
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ICommand command;
        User user = (User) request.getSession().getAttribute("currentUser");
        String action = request.getParameter("action");
        log.info("[Controller] Get action:[" + action + "]");
        if (user == null)
        {
//            command = commandProvider.getCommand("closeSession");
//            log.warn("[Controller] Session [" + request.getSession().getId() + "}has been expired!");
        }
//        else
            command = commandProvider.getCommand(action);
        try
        {
            command.execute(request, response);
        }
        catch (ServiceException e)
        {
            log.warn("[Controller] Service exception: lost connection to DB.");
            response.sendRedirect("Controller?action=sign_in&errorMessage=Lost connection to DB. try later...");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        super.doGet(request, response);
    }
}
