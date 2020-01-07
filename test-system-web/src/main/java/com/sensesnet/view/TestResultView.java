package com.sensesnet.view;

import com.sensesnet.pojo.test.TestAnswer;
import com.sensesnet.pojo.test.TestQuestion;
import com.sensesnet.pojo.test.TestResult;
import lombok.*;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestResultView
{
    public TestQuestion testQuestion;
    public List<TestAnswer> testAnswerList;
    public TestResult testResult;
}
