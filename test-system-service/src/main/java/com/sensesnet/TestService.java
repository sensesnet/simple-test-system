package com.sensesnet;


import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dto.TestDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.Test;
import com.sensesnet.pojo.test.TestAnswer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public interface TestService
{

    /**
     * gets list of tests
     *
     * @return
     * @throws ServiceException
     */
    List<Test> getAllTests() throws ServiceException;

    /**
     * gets test by id
     *
     * @param testId
     * @return
     * @throws ServiceException
     */
    Test getTest(Integer testId) throws ServiceException;

    /**
     * create new test
     *
     * @param test
     * @throws ServiceException
     */
    void getTest(Test test) throws ServiceException;

    /**
     * create new test
     *
     * @param test
     * @throws ServiceException
     */
    void createTest(Test test) throws ServiceException;

    /**
     * update test
     *
     * @param test
     * @throws ServiceException
     */
    void updateTest(Test test) throws ServiceException;

    /**
     * remove test
     *
     * @param test
     * @throws ServiceException
     */
    void removeTest(Test test) throws ServiceException;

    /**
     * build Test entity
     *
     * @param request
     * @return
     */
    Test buildTest(HttpServletRequest request);

    /**
     * gets all tests count
     *
     * @return
     */
    int getAllTestsCount();

    /**
     * gets test DTO
     *
     * @param testId
     * @return
     */
    TestDto getTestDto(Integer testId);

    /**
     * gets list of TestDto
     *
     * @param test
     * @return
     */
    List<TestDto> getAllTestWithTheirAnswers(Test test);

    /**
     *
     * @param result
     * @return
     */
    boolean isTestCompleted(Integer testId,Integer result) throws ServiceException;
}
