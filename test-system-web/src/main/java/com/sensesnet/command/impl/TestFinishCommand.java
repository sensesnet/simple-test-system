package com.sensesnet.command.impl;

import com.sensesnet.*;
import com.sensesnet.command.Command;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.TestProcess;
import com.sensesnet.pojo.test.TestQuestion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class TestFinishCommand implements Command
{
    private static final Logger log = LogManager.getLogger(TestFinishCommand.class);
    private TestQuestionService testQuestionService = ServiceFactory.getInstance().getTestQuestionService();
    private TestProcessService testProcessService = ServiceFactory.getInstance().getTestProcessService();
    private TestService testService = ServiceFactory.getInstance().getTestService();
    private TestAnswerService answerService = ServiceFactory.getInstance().getTestAnswerService();
    private TestResultService resultService = ServiceFactory.getInstance().getTestResultService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException
    {

        RequestDispatcher dispatcher = request
                .getRequestDispatcher(ConstantProvider.getPagePath().TEST_RESULT_PAGE);
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer testId = (Integer) request.getSession().getAttribute("testId");
        String testProcessId = (String) request.getSession().getAttribute("testProcessId");

        List<TestQuestion> testQuestionList = testQuestionService.listOfQuestionsByTestId(testId);

        Integer result = 0;

        for (TestQuestion question : testQuestionList)
        {
            String questionParam = "question_" + question.getQuestionId();
            if (request.getParameterValues(questionParam) == null)
            {
                resultService.addAnswerToResult(testProcessId, question.getQuestionId(), 0);
            }
            else
            {
                for (String answer : request.getParameterValues(questionParam))
                {
                    resultService.addAnswerToResult(testProcessId, question.getQuestionId(), Integer.valueOf(answer));
                    if (Integer.valueOf(answer).equals(question.getAnswerId()))
                    {
                        result++;
                    }
                }
            }
        }
        boolean isCompleted = testService.isTestCompleted(testId, result);
        TestProcess testProcess = testProcessService.getTestProcess(testProcessId);
        testProcess.setMainResultValue(result);
        testProcess.setCompleted(isCompleted);
        testProcessService.updateProcess(testProcess);

        request.getSession().setAttribute("result", result);
        request.getSession().setAttribute("isCompleted", isCompleted);
        request.getSession().setAttribute("numberOfQuestion", testQuestionList.size());
        request.getSession().setAttribute("testEntity", testService.getTest(testId));
        dispatcher.forward(request, response);
    }
}
