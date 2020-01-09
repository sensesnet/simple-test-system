package com.sensesnet;

import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.TestAnswer;
import com.sensesnet.pojo.test.TestQuestion;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public interface TestAnswerService
{
    List<TestAnswer> listOfAnswersByQuestionId(Integer questionId) throws ServiceException;

    void removeAnswerById(Integer answerId) throws ServiceException;

    void addAnswer(TestAnswer answer) throws ServiceException;
}
