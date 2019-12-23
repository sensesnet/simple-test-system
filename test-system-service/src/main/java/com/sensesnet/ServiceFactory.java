package com.sensesnet;

import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.impl.*;

import java.sql.SQLException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: Service provdes
 */
public class ServiceFactory
{
    private static final ServiceFactory instance = new ServiceFactory();

    private final IUserService userService = new UserService();
    private final UserRoleService userRoleService = new UserRoleService();
    private final TestAnswerService testAnswerService = new TestAnswerService();
    private final TestProcessService testProcessService = new TestProcessService();
    private final TestQuestionService testQuestionService = new TestQuestionService();
    private final TestResultService testResultService = new TestResultService();
    private final TestService testService = new TestService();

    public static ServiceFactory getInstance()
    {
        return instance;
    }

    public IUserService getUserService()
    {
        return userService;
    }

    public UserRoleService getUserRoleService()
    {
        return userRoleService;
    }

    public TestAnswerService getTestAnswerService()
    {
        return testAnswerService;
    }

    public TestProcessService getTestProcessService()
    {
        return testProcessService;
    }

    public TestQuestionService getTestQuestionService()
    {
        return testQuestionService;
    }

    public TestResultService getTestResultService()
    {
        return testResultService;
    }

    public TestService getTestService()
    {
        return testService;
    }

    /**
     * Initialize connection pool
     */
    public void initConnectionPool() throws ServiceException
    {
        try
        {
            DaoFactory.initConnectionPool();
        }
        catch (ConnectionPoolException e)
        {
            throw new ServiceException("Connection pool has not been initialized!", e);
        }
    }

    /**
     * Destroy connection pool
     */
    public void destroyConnectionPool() throws ServiceException
    {
        try
        {
            DaoFactory.destroyConnectionPool();
        }
        catch (ConnectionPoolException | SQLException e)
        {
            throw new ServiceException("Connection pool has not been destroyed!", e);
        }
    }
}
