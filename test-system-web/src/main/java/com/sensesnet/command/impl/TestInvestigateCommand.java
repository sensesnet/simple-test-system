package com.sensesnet.command.impl;

import com.sensesnet.*;
import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.TestAnswer;
import com.sensesnet.pojo.test.TestProcess;
import com.sensesnet.pojo.test.TestQuestion;
import com.sensesnet.pojo.test.TestResult;
import com.sensesnet.util.DateUtils;
import com.sensesnet.view.TestResultView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class TestInvestigateCommand implements Command
{
    private static final Logger log = LogManager.getLogger(TestInvestigateCommand.class);

    private TestQuestionService testQuestionService = ServiceFactory.getInstance().getTestQuestionService();
    private TestProcessService testProcessService = ServiceFactory.getInstance().getTestProcessService();
    private TestService testService = ServiceFactory.getInstance().getTestService();
    private TestAnswerService answerService = ServiceFactory.getInstance().getTestAnswerService();
    private TestResultService resultService = ServiceFactory.getInstance().getTestResultService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(ConstantProvider.getPagePath().TEST_INVESTIGATE_PAGE);
        TestProcess testProcess = testProcessService.getTestProcess(request.getParameter("testProcessId"));
        HashMap<Integer, TestResult> testResultList = resultService.getTestResultListByProcessId(testProcess.getTestProcessId());

        Integer testId = testProcess.getTestId();
        List<TestResultView> testViewList = new ArrayList<TestResultView>();
        for (TestQuestion question : testQuestionService.listOfQuestionsByTestId(testId))
        {
            List<TestAnswer> answerList = answerService.listOfAnswersByQuestionId(question.getQuestionId());
            testViewList.add(new TestResultView(question, answerList, testResultList.get(question.getQuestionId())));
        }
        if (testViewList.size() == 0)
        {
            response.sendRedirect("Controller?action=home&errorMessage=Have no found any questions. The test systems is empty.");
            return;
        }

        request.getSession().setAttribute("testResultList", testViewList);
        dispatcher.forward(request, response);
        log.info("[" + this.getClass().getName() + "] Test question result list has been forward.");
    }
}
