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

    public TestProcess getByIdentifier(TestProcess entity)
    {
        return null;
    }


    public List<TestProcess> getListOfEntity()
    {
        return null;
    }


    public void addEntity(TestProcess entity)
    {

    }


    public void removeEntity(TestProcess entity)
    {

    }


    public void editEntity(TestProcess entity)
    {

    }

    @Override
    public void createTestProcess(String testProcessId, String currentDate, Integer userId, Integer testId, Integer result, Boolean isCompleted) throws ServiceException
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
                .result(result)
                .isCompleted(isCompleted).build();
    }
}
