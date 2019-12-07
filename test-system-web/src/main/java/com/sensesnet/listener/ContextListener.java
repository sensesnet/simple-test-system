package com.sensesnet.listener;

import com.sensesnet.implementation.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * ContextListener
 */
@WebListener
public class ContextListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
       //before all sevlets
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        // after all servlets destroyed
    }
}
