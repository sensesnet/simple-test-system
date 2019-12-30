package com.sensesnet;

import com.sensesnet.exception.ServiceException;

import java.util.Date;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public interface TestProcessService
{
    void createTestProcess(String testProcessId, String currentDate, Integer userId, Integer testId, Integer result, Boolean isCompleted) throws ServiceException;
}
