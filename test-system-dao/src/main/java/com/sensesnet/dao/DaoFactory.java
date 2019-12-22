package com.sensesnet.dao;

import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.test.dao.*;
import com.sensesnet.dao.user.dao.UserDao;
import com.sensesnet.dao.user.dao.UserInfoDao;
import com.sensesnet.dao.user.dao.UserRoleDao;
import com.sensesnet.pojo.authentication.User;
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

    private static TestAnswerDao testAnswerDao = new TestAnswerDao();
    private static TestDao testDao = new TestDao();
    private static TestProcessDao testProcessDao = new TestProcessDao();
    private static TestQuestionDao testQuestionDao = new TestQuestionDao();
    private static TestResultDao testResultDao = new TestResultDao();
    private static UserDao userDao = new UserDao();
    private static UserInfoDao userInfoDao = new UserInfoDao();
    private static UserRoleDao userRoleDao = new UserRoleDao();

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

    public static UserInfoDao getUserInfoDao()
    {
        return userInfoDao;
    }

    public static UserRoleDao getUserRoleDao()
    {
        return userRoleDao;
    }

}
