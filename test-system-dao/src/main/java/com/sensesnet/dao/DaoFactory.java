package com.sensesnet.dao;

import com.sensesnet.dao.test.dao.*;
import com.sensesnet.dao.user.dao.UserDao;
import com.sensesnet.dao.user.dao.UserInfoDao;
import com.sensesnet.dao.user.dao.UserRoleDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: DAO Factory
 */
public class DaoFactory
{
    private static final Logger log = LogManager.getLogger(DaoFactory.class);

    private static TestAnswerDao testAnswerDao;
    private static TestDao testDao;
    private static TestProcessDao testProcessDao;
    private static TestQuestionDao testQuestionDao;
    private static TestResultDao testResultDao;
    private static UserDao userDao;
    private static UserInfoDao userInfoDao;
    private static UserRoleDao userRoleDao;

    public static TestAnswerDao getTestAnswerDao()
    {
        return testAnswerDao == null ? testAnswerDao = new TestAnswerDao() : testAnswerDao;
    }

    public static TestDao getTestDao()
    {
        return testDao == null ? testDao = new TestDao() : testDao;
    }

    public static TestProcessDao getTestProcessDao()
    {
        return testProcessDao == null ? testProcessDao = new TestProcessDao() : testProcessDao;
    }

    public static TestQuestionDao getTestQuestionDao()
    {
        return testQuestionDao == null ? testQuestionDao = new TestQuestionDao() : testQuestionDao;
    }

    public static TestResultDao getTestResultDao()
    {
        return testResultDao == null ? testResultDao = new TestResultDao() : testResultDao;
    }

    public static UserDao getUserDao()
    {
        return userDao == null ? userDao = new UserDao() : userDao;
    }

    public static UserInfoDao getUserInfoDao()
    {
        return userInfoDao == null ? userInfoDao = new UserInfoDao() : userInfoDao;
    }

    public static UserRoleDao getUserRoleDao()
    {
        return userRoleDao == null ? userRoleDao = new UserRoleDao() : userRoleDao;
    }
}
