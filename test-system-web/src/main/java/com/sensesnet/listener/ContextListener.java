package com.sensesnet.listener;

import com.sensesnet.ServiceFactory;
import com.sensesnet.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * ContextListener
 */
@WebListener
public class ContextListener implements ServletContextListener
{
    private static final Logger log = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        try
        {
            ServiceFactory.getInstance().initConnectionPool();
            log.info("[ContextListener] Classes "
                    + "[ConnectionPool; DaoFactory; ServiceFactory] has been initialized.");
        }
        catch (ServiceException e)
        {
            log.error("[ContextListener] Connection to DB has not been initialized. "
                    + "Exception: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        try
        {
            ServiceFactory.getInstance().destroyConnectionPool();
            log.info("[ContextListener] ConnectionPool has been destroyed");
        }
        catch (ServiceException e)
        {
            log.error(
                    "[ContextListener] Connection to DB has not been destroyed. "
                            + "Exception: " + e.getLocalizedMessage());
        }
    }
}
