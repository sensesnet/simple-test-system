package com.sensesnet.dao;

import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.test.dao.*;
import com.sensesnet.dao.user.dao.UserDao;
import com.sensesnet.dao.user.dao.UserRoleDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: DAO Factory
 */
public class DaoFactory
{
    private static final Logger log = LogManager.getLogger(DaoFactory.class);

    private static final TestAnswerDao testAnswerDao = new TestAnswerDao();
    private static final TestDao testDao = new TestDao();
    private static final TestProcessDao testProcessDao = new TestProcessDao();
    private static final TestQuestionDao testQuestionDao = new TestQuestionDao();
    private static final TestResultDao testResultDao = new TestResultDao();
    private static final UserDao userDao = new UserDao();
    private static final UserRoleDao userRoleDao = new UserRoleDao();

    /**
     * Initialize connection pool
     * @throws ConnectionPoolException
     */
    public static void initConnectionPool() throws ConnectionPoolException
    {
        ConnectionPool.getInstance().initPoolData();
    }

    /**
     * Destroy connection pool
     *
     * @throws ConnectionPoolException
     * @throws SQLException
     */
    public static void destroyConnectionPool() throws ConnectionPoolException, SQLException
    {
        ConnectionPool.getInstance().destroyConnectionPool();
    }

    /**
     * Get test answer dao
     *
     * @return {@link #testAnswerDao}
     */
    public static TestAnswerDao getTestAnswerDao() { return testAnswerDao; }

    public static TestDao getTestDao() { return testDao; }

    public static TestProcessDao getTestProcessDao() { return testProcessDao; }

    public static TestQuestionDao getTestQuestionDao()
    {
        return testQuestionDao;
    }

    public static TestResultDao getTestResultDao()
    {
        return testResultDao;
    }

    public static UserDao getUserDao()
    {
        return userDao;
    }

    public static UserRoleDao getUserRoleDao()
    {
        return userRoleDao;
    }

}
