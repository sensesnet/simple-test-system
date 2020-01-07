package com.sensesnet;

import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.TestProcess;

import java.util.Date;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public interface TestProcessService
{
    void createTestProcess(String testProcessId, String currentDate, Integer userId, Integer testId, Integer result, boolean isCompleted) throws ServiceException;

    void updateProcess(TestProcess testProcess) throws ServiceException;

    TestProcess getTestProcess(String testProcessId) throws ServiceException;

    List<TestProcess> getUserTestHistory(Integer userId) throws ServiceException;
}
