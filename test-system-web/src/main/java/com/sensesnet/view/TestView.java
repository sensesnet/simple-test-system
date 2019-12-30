package com.sensesnet.view;

import com.sensesnet.dao.test.dao.TestAnswerDao;
import com.sensesnet.pojo.test.TestAnswer;
import com.sensesnet.pojo.test.TestQuestion;
import lombok.*;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestView
{
    public TestQuestion testQuestion;
    public List<TestAnswer> testAnswerList;
}
