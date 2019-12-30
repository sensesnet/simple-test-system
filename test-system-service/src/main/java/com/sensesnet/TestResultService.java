package com.sensesnet;

import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.TestProcess;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public interface TestResultService
{
    void addAnswerToResult(String testProcessId, Integer questionId, Integer answer) throws ServiceException;
}
