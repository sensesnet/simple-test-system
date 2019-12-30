package com.sensesnet.impl;

import com.sensesnet.TestService;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.test.dao.TestDao;
import com.sensesnet.dao.user.dao.UserDao;
import com.sensesnet.dto.TestDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: Test service
 */
public class TestServiceImpl implements TestService
{

    private final Logger log = LogManager.getLogger(TestServiceImpl.class);
    private TestDao testDao = DaoFactory.getTestDao();

    @Override
    public List<Test> getAllTests() throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Get list with all users.");
        try
        {
            return testDao.getListOfEntity();
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Test list has not found at test system. Service exception", e);
        }
    }

    @Override
    public Test getTest(Integer testId) throws ServiceException
    {
        return null;
    }

    @Override
    public void getTest(Test test) throws ServiceException
    {

    }

    @Override
    public void createTest(Test test) throws ServiceException
    {

    }

    @Override
    public void updateTest(Test test) throws ServiceException
    {

    }

    @Override
    public void removeTest(Test test) throws ServiceException
    {

    }

    @Override
    public Test buildTest(HttpServletRequest request)
    {
        return null;
    }

    @Override
    public int getAllTestsCount()
    {
        return 0;
    }

    @Override
    public TestDto getTestDto(Integer testId)
    {
        return null;
    }

    @Override
    public List<TestDto> getAllTestWithTheirAnswers(Test test)
    {
        return null;
    }
}
