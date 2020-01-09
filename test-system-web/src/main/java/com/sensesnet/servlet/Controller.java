package com.sensesnet.servlet;

import com.sensesnet.command.CommandProvider;
import com.sensesnet.command.Command;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.exception.DaoException;
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
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        process(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            Command command = commandProvider.getCommand(request);
            command.execute(request, response);
        }
        catch (ServiceException e)
        {
            log.warn("[Controller] Service exception: lost connection to DB.");
            response.sendRedirect("Controller?action=sign_in&errorMessage=Lost connection to DB. try later...");
        }
    }
}
