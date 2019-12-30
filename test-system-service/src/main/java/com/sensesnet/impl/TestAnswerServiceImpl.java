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

    public TestAnswer getByIdentifier(TestAnswer entity)
    {
        return null;
    }


    public List<TestAnswer> getListOfEntity()
    {
        return null;
    }


    public void addEntity(TestAnswer entity)
    {

    }


    public void removeEntity(TestAnswer entity)
    {

    }


    public void editEntity(TestAnswer entity)
    {

    }

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
}
