package com.sensesnet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
@WebListener
public class ContextListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        // before all servlets starts
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        // after all servlets destroyed
    }
}
