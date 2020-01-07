package com.sensesnet;

import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.TestProcess;
import com.sensesnet.pojo.test.TestResult;

import java.util.HashMap;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public interface TestResultService
{
    void addAnswerToResult(String testProcessId, Integer questionId, Integer answer) throws ServiceException;

    /**
     * key: question Id
     * value: test result for current question
     * @param testProcessId
     * @return
     * @throws ServiceException
     */
    HashMap<Integer,TestResult> getTestResultListByProcessId(String testProcessId) throws ServiceException;

    TestResult getTestResultByProcessIdAndQuestionId(String testProcessId, Integer questionId) throws ServiceException;

}
