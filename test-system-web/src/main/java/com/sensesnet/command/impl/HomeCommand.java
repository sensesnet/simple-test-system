package com.sensesnet.command.impl;

import com.sensesnet.*;
import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.impl.UserRoleServiceImpl;
import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.authentication.UserRole;
import com.sensesnet.pojo.test.TestProcess;
import com.sensesnet.pojo.test.TestResult;
import com.sensesnet.view.TestHistoryView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Home page
 * - get User role
 * - add param to session
 * - return redirect to depended page by role ('Admin' or 'User').
 */
public class HomeCommand implements Command
{
    private static final Logger log = LogManager.getLogger(HomeCommand.class);
    private UserRoleService userRoleService = ServiceFactory.getInstance().getUserRoleService();
    private TestProcessService testProcessService = ServiceFactory.getInstance().getTestProcessService();
    private TestService testService = ServiceFactory.getInstance().getTestService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user;
        UserRole userRole;
        RequestDispatcher dispatcher;

        user = (User) request.getSession().getAttribute("currentUser");
        try
        {
            userRole = userRoleService.getRoleById(user.getUserRole());
            request.getSession().setAttribute("userRole", userRoleService.getRoleById(user.getUserRole()));

            if (userRole.getRoleName().equalsIgnoreCase("Admin"))
            {
                dispatcher = request.getRequestDispatcher(ConstantProvider.getPagePath().HOME_ADMIN_PAGE);
                dispatcher.forward(request, response);
            }

            if (userRole.getRoleName().equalsIgnoreCase("User"))
            {
                dispatcher = request.getRequestDispatcher(ConstantProvider.getPagePath().HOME_USER_PAGE);
                List<TestProcess> testProcessList = testProcessService.getUserTestHistory(user.getUserId());
                List<TestHistoryView> resultHistoryList = new ArrayList<>();
                for(TestProcess process: testProcessList){
                    resultHistoryList.add(new TestHistoryView(process,testService.getTest(process.getTestId())));
                }
                Collections.reverse(resultHistoryList);
                request.getSession().setAttribute("resultHistory", resultHistoryList);
                if(resultHistoryList.size()==0){
                    request.getSession().setAttribute("message", "User has no any results!");
                }
                dispatcher.forward(request, response);
            }
        }
        catch (ServiceException e)
        {
            log.warn("[HomeCommand] Security exception: lost connection to DB.");
            response.sendRedirect("Controller?action=sign_in&errorMessage=Lost connection to DB. try later...");
        }
    }
}
