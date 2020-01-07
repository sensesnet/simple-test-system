package com.sensesnet.command.impl;

import com.sensesnet.*;
import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.dto.TestDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.test.Test;
import com.sensesnet.pojo.test.TestAnswer;
import com.sensesnet.pojo.test.TestQuestion;
import com.sensesnet.util.DateUtils;
import com.sensesnet.view.TestView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class TestProcessCommand implements Command
{
    private static final Logger log = LogManager.getLogger(TestProcessCommand.class);
    private TestQuestionService testQuestionService = ServiceFactory.getInstance().getTestQuestionService();
    private TestProcessService testProcessService = ServiceFactory.getInstance().getTestProcessService();
    private TestAnswerService answerService = ServiceFactory.getInstance().getTestAnswerService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {

        RequestDispatcher dispatcher = request
                .getRequestDispatcher(ConstantProvider.getPagePath().TEST_PROCESS_PAGE);
        Integer testId = Integer.valueOf(request.getParameter("testId"));
        List<TestView> testViewList = new ArrayList<TestView>();
        List<TestQuestion> testQuestionList = testQuestionService.listOfQuestionsByTestId(testId);

        for (TestQuestion question : testQuestionList)
        {
            List<TestAnswer> answerList = answerService.listOfAnswersByQuestionId(question.getQuestionId());
            testViewList.add(new TestView(question, answerList));
        }

        if (testViewList.size() == 0)
        {
            response.sendRedirect("Controller?action=home&errorMessage=Have no found any questions. The test systems is empty.");
            return;
        }

        User user = (User) request.getSession().getAttribute("currentUser");
        request.setAttribute("testQuestionList", testViewList);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String processId = timestamp.getTime() + "-" + user.getUserId();
        request.getSession().setAttribute("testProcessId", processId);
        request.getSession().setAttribute("userId", user.getUserId());
        request.getSession().setAttribute("testId", testId);
        String date = DateUtils.getCurrentDate();

        testProcessService
                .createTestProcess(
                        processId,
                        date,
                        user.getUserId(),
                        testId, 0, false);


        dispatcher.forward(request, response);
        log.info("[" + this.getClass().getName() + "] Test question list has been forward.");
    }
}
