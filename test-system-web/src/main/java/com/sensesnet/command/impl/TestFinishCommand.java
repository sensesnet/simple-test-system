package com.sensesnet.command.impl;

import com.sensesnet.*;
import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.TestQuestion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class TestFinishCommand implements Command
{
    private static final Logger log = LogManager.getLogger(TestViewCommand.class);
    private TestQuestionService testQuestionService = ServiceFactory.getInstance().getTestQuestionService();
    private TestProcessService testProcessService = ServiceFactory.getInstance().getTestProcessService();
    private TestAnswerService answerService = ServiceFactory.getInstance().getTestAnswerService();
    private TestResultService resultService = ServiceFactory.getInstance().getTestResultService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {

        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer testId = Integer.valueOf(request.getParameter("testId"));
        String testProcessId = request.getParameter("testProcessId");

        List<TestQuestion> testQuestionList = testQuestionService.listOfQuestionsByTestId(Integer.valueOf(request.getParameter("testId")));
        for (TestQuestion question : testQuestionList)
        {
            for (String answer : request.getParameterValues(
                    "question_" + testQuestionList.indexOf(question) + 1))
            {
//                resultService.addAnswerToResult(testProcessId, question.getQuestionId(),answer);
            }
        }

        String[] checks = request.getParameterValues("question_1");
        for (int i = 0; i <= checks.length; i++)
        {
            System.out.print(checks[i]);
        }
        String[] checks2 = request.getParameterValues("question_2");
    }
}
