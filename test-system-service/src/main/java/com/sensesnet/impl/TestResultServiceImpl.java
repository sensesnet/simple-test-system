package com.sensesnet.impl;

import com.sensesnet.TestResultService;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.test.dao.TestDao;
import com.sensesnet.dao.test.dao.TestResultDao;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.TestProcess;
import com.sensesnet.pojo.test.TestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: Test result service
 */
public class TestResultServiceImpl implements TestResultService
{

    private final Logger log = LogManager.getLogger(TestServiceImpl.class);
    private TestResultDao testResultDao = DaoFactory.getTestResultDao();

    public TestResult getByIdentifier(TestResult entity)
    {
        return null;
    }


    public List<TestResult> getListOfEntity()
    {
        return null;
    }


    public void addEntity(TestResult entity)
    {

    }


    public void removeEntity(TestResult entity)
    {

    }


    public void editEntity(TestResult entity)
    {

    }

    @Override
    public void addAnswerToResult(String testProcessId, Integer questionId, Integer answerId) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] "
                + "Add answer:[" + testProcessId + "|" + questionId + "|" + answerId + "].");
        try
        {
            testResultDao.addEntity(buildTestResult(testProcessId, questionId, answerId));
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Answer [" + testProcessId + " | " + questionId + " | " + answerId + "] has NOT added.", e);
        }
    }

    @Override
    public HashMap<Integer, TestResult> getTestResultListByProcessId(String testProcessId) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Get result list by process id: " + testProcessId);
        try
        {
            return testResultDao.getListOfEntityByProcessId(testProcessId);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Result list has not found at test system. Service exception", e);
        }
    }

    @Override
    public TestResult getTestResultByProcessIdAndQuestionId(String testProcessId, Integer questionId) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] "
                + "Get test result by process and question id:[" + testProcessId + " | " + questionId + "]");
        try
        {
            return testResultDao.getTestResultByProcessIdAndQuestionId(testProcessId, questionId);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Test result " + testProcessId + " has NOT found.", e);
        }
    }

    private TestResult buildTestResult(String testProcessId,
                                       Integer questionId,
                                       Integer answerId)
    {
        return TestResult.builder()
                .testProcessId(testProcessId)
                .questionId(questionId)
                .answerId(answerId).build();
    }
}
