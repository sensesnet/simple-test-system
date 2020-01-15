package com.sensesnet.command.impl;

import com.sensesnet.ServiceFactory;
import com.sensesnet.TestAnswerService;
import com.sensesnet.TestQuestionService;
import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.test.TestAnswer;
import com.sensesnet.pojo.test.TestQuestion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * Update clarification command
 */
public class SaveClarificationCommand implements Command
{
    private static final Logger log = LogManager.getLogger(SaveQuestionCommand.class);
    private TestAnswerService testAnswerService = ServiceFactory.getInstance().getTestAnswerService();
    private TestQuestionService testQuestionService = ServiceFactory.getInstance().getTestQuestionService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {
        Integer questionId = Integer.valueOf(request.getParameter("questionId"));
        String newClarificationDesc = String.valueOf(request.getParameter("newClarification"));
        TestQuestion testQuestion = testQuestionService.getQuestionById(questionId);
        if (newClarificationDesc != null & testQuestion != null)
        {
            testQuestion.setQuestionClarification(newClarificationDesc);
            testQuestionService.updateQuestion(testQuestion);
        }
        request.setAttribute("editQuestion", testQuestion);
        List<TestAnswer> answersList = testAnswerService.listOfAnswersByQuestionId(questionId);
        request.setAttribute("listOfAnswers", answersList);
        RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantProvider.getPagePath().QUESTION_EDIT_PAGE);

        log.info("New question clarification has been added: " + newClarificationDesc);
        dispatcher.forward(request, response);
    }
}
