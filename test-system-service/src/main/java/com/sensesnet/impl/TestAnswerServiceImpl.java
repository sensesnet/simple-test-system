package com.sensesnet.impl;

import com.sensesnet.TestAnswerService;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.test.dao.TestAnswerDao;
import com.sensesnet.dao.test.dao.TestDao;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.TestAnswer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: Test answer service
 */
public class TestAnswerServiceImpl implements TestAnswerService
{

    private final Logger log = LogManager.getLogger(TestServiceImpl.class);
    private TestAnswerDao testAnswerDao = DaoFactory.getTestAnswerDao();

    @Override
    public List<TestAnswer> listOfAnswersByQuestionId(Integer questionId) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Get answer list for test [" + questionId + "]");
        try
        {
            return testAnswerDao.listOfAnswersByQuestionId(questionId);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Answer list has not found at test system. Service exception", e);
        }
    }

    @Override
    public void removeAnswerById(Integer answerId) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Remove answer by id [" + answerId + "]");
        try
        {
            testAnswerDao.removeAnswerById(answerId);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Answer has not found at test system. Service exception", e);
        }
    }

    @Override
    public void addAnswer(TestAnswer answer) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Add answer: [" + answer.getAnswerDescription() + "] "
                + "for question [" + answer.getQuestionId() + "]");
        try
        {
            testAnswerDao.addEntity(answer);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Answer has not found at test system. Service exception", e);
        }
    }
}
