package com.sensesnet.command.impl;

import com.sensesnet.TestAnswerService;
import com.sensesnet.TestService;
import com.sensesnet.ServiceFactory;
import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.authentication.UserRole;
import com.sensesnet.pojo.test.Test;
import org.apache.logging.log4j.LogManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Logger;


/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class TestViewCommand implements Command
{
    private static final Logger log = LogManager.getLogger(TestViewCommand.class);
    private TestService testService = ServiceFactory.getInstance().getTestService();
    private TestAnswerService answerService = ServiceFactory.getInstance().getTestAnswerService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {
        RequestDispatcher dispatcher;
        List<Test> testList = testService.getAllTests();
        User user = (User) request.getSession().getAttribute("currentUser");
        UserRole userRole = (UserRole) request.getSession().getAttribute("userRole");
        if (testList.size() == 0)
        {
            response.sendRedirect("Controller?action=home&errorMessage=Have no found any tests. The test systems is empty.");
            return;
        }
        request.setAttribute("testList", testList);

        if (userRole.getRoleName().equalsIgnoreCase("Admin"))
        {
            dispatcher = request.getRequestDispatcher(ConstantProvider.getPagePath().TEST_LIST_FOR_ADMIN_PAGE);
            dispatcher.forward(request, response);
        }

        if (userRole.getRoleName().equalsIgnoreCase("User"))
        {
            dispatcher = request.getRequestDispatcher(ConstantProvider.getPagePath().TEST_LIST_FOR_USER_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
