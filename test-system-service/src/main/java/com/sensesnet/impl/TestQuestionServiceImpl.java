package com.sensesnet.impl;

import com.sensesnet.TestQuestionService;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.test.dao.TestQuestionDao;
import com.sensesnet.dao.user.dao.UserDao;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.Test;
import com.sensesnet.pojo.test.TestQuestion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: Test question service
 */
public class TestQuestionServiceImpl implements TestQuestionService
{

    private static final Logger log = LogManager.getLogger(TestQuestionServiceImpl.class);
    private TestQuestionDao testQuestionDao = DaoFactory.getTestQuestionDao();

    @Override
    public List<TestQuestion> listOfQuestionsByTestId(Integer testId) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Get list of questions by test id [" + testId + "].");
        try
        {
            return testQuestionDao.listOfQuestionsByTestId(testId);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Questions list has not found at test system. Service exception", e);
        }
    }

    @Override
    public TestQuestion getQuestionById(Integer questionId) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Get questions by id [" + questionId + "].");
        try
        {
            return testQuestionDao.getQuestionsById(questionId);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Questions [" + questionId + "] has not found at test system. Service exception", e);
        }
    }
}
