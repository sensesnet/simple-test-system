package com.sensesnet.impl;

import com.sensesnet.TestProcessService;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.test.dao.TestProcessDao;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.TestProcess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: Test process service
 */
public class TestProcessServiceImpl implements TestProcessService
{

    private static final Logger log = LogManager.getLogger(TestQuestionServiceImpl.class);
    private TestProcessDao testProcessDao = DaoFactory.getTestProcessDao();

    @Override
    public void createTestProcess(String testProcessId, String currentDate, Integer userId, Integer testId, Integer result, boolean isCompleted) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Start test process:[" + testProcessId + "]");
        try
        {
            testProcessDao.addEntity(buildTestProcess(testProcessId, currentDate, userId, testId, result, isCompleted));
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "New test process " + testProcessId + " has NOT started.", e);
        }
    }

    @Override
    public void updateProcess(TestProcess testProcess) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Update test process:[" + testProcess.getTestProcessId() + "]");
        try
        {
            testProcessDao.editEntity(testProcess);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Process " + testProcess.getTestProcessId() + " has NOT updated.", e);
        }
    }

    @Override
    public TestProcess getTestProcess(String testProcessId) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Get test process:[" + testProcessId + "]");
        try
        {
            return testProcessDao.getByIdentifier(testProcessId);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "New test process " + testProcessId + " has NOT started.", e);
        }
    }

    @Override
    public List<TestProcess> getUserTestHistory(Integer userId) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Get test history by user id:[" + userId + "]");
        try
        {
            return testProcessDao.getHistoryByUserId(userId);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Test history process " + userId + " has NOT found.", e);
        }
    }

    private TestProcess buildTestProcess(String testProcessId,
                                         String currentDate,
                                         Integer userId,
                                         Integer testId,
                                         Integer result,
                                         Boolean isCompleted)
    {
        return TestProcess.builder()
                .testProcessId(testProcessId)
                .testProcessDate(currentDate)
                .testId(testId)
                .userId(userId)
                .mainResultValue(result)
                .isCompleted(isCompleted).build();
    }
}
