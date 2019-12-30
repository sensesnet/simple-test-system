package com.sensesnet;

import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.Test;
import com.sensesnet.pojo.test.TestQuestion;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public interface TestQuestionService
{
    List<TestQuestion> listOfQuestionsByTestId(Integer testId) throws ServiceException;
}
