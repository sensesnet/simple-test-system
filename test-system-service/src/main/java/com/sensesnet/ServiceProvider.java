package com.sensesnet;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.user.dao.UserInfoDao;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.implementation.*;
import com.sensesnet.pojo.test.TestProcess;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: Service provdes
 */
public class ServiceProvider
{
    private static final ServiceProvider instance = new ServiceProvider();

    private final UserService userService = new UserService();
    private final UserRoleService userRoleService = new UserRoleService();
    private final UserInfoService userInfoService = new UserInfoService();
    private final TestAnswerService testAnswerService = new TestAnswerService();
    private final TestProcessService testProcessService = new TestProcessService();
    private final TestQuestionService testQuestionService = new TestQuestionService();
    private final TestResultService testResultService = new TestResultService();
    private final TestService testService = new TestService();

    public static ServiceProvider getInstance()
    {
        return instance;
    }

    public UserService getUserService()
    {
        return userService;
    }

    public UserRoleService getUserRoleService()
    {
        return userRoleService;
    }

    public UserInfoService getUserInfoService()
    {
        return userInfoService;
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
        catch (Exception e)
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
        catch (Exception e)
        {
            throw new ServiceException("Connection pool has not been destroyed!", e);
        }
    }
}
