package com.sensesnet.command.impl;

import com.sensesnet.ServiceFactory;
import com.sensesnet.TestAnswerService;
import com.sensesnet.TestQuestionService;
import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.dto.UserDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.test.Test;
import com.sensesnet.pojo.test.TestAnswer;
import com.sensesnet.pojo.test.TestQuestion;
import com.sensesnet.view.TestView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class TestEditCommand implements Command
{
    private static final Logger log = LogManager.getLogger(TestEditCommand.class);
    private TestQuestionService testQuestionService = ServiceFactory.getInstance().getTestQuestionService();
    private TestAnswerService answerService = ServiceFactory.getInstance().getTestAnswerService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(ConstantProvider.getPagePath().TEST_EDIT);
        List<TestView> testViewList = new ArrayList<TestView>();
        List<TestQuestion> testQuestionList =
                testQuestionService.listOfQuestionsByTestId(Integer.valueOf(request.getParameter("testId")));

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
        request.setAttribute("testQuestionList", testViewList);
        dispatcher.forward(request, response);
        log.info("[" + this.getClass().getName() + "] Test question list has been forward.");
    }
}
