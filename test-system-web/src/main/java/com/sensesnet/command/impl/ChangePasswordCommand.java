package com.sensesnet.command.impl;

import com.sensesnet.ServiceFactory;
import com.sensesnet.UserService;
import com.sensesnet.command.Command;
import com.sensesnet.constant.ConstantProvider;
import com.sensesnet.dto.UserDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.sensesnet.util.EncryptPassword.getEncryptPassword;
import static com.sensesnet.util.HttpRequestValidator.isPost;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * Change password command
 * - check and update password
 */
public class ChangePasswordCommand implements Command
{

    private static final Logger log = LogManager.getLogger(ChangePasswordCommand.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (!isPost(request))
        {
            request
                    .getRequestDispatcher(ConstantProvider.getPagePath().CHANGE_PASS)
                    .forward(request, response);
            return;
        }
        String oldPassword = request
                .getParameter(ConstantProvider.getRequestParameter().OLD_PASSWORD);
        String password = request
                .getParameter(ConstantProvider.getRequestParameter().PASSWORD);
        String confirmPassword = request
                .getParameter(ConstantProvider.getRequestParameter().CONFIRM_PASSWORD);
        if (!password.equals(confirmPassword))
        {
            response.sendRedirect("Controller?action=change_password&errorMessage=Password in not equals...");
            return;
        }
        try
        {
            User user = (User) request.getSession().getAttribute("currentUser");
            if (user == null)
            {
                response.sendRedirect("Controller?action=sign_in&errorMessage=Account is not found, try again...");
                return;
            }
            if (!user.getUserPassword().equals(getEncryptPassword(oldPassword)))
            {
                response.sendRedirect("Controller?action=change_password&errorMessage=Old Password in not equals...");
                return;
            }
            user.setUserPassword(getEncryptPassword(password));

            userService.updateUser(user);
            request.getSession().setAttribute("currentUser", user);
            response.sendRedirect("Controller?action=home&message=Password has been changed successfully.");
        }
        catch (ServiceException e)
        {
            log.warn("[LoginFilter] Security exception: lost connection to DB.");
            response.sendRedirect("Controller?action=sign_in&errorMessage=Lot connection to DB. try later...");
        }
    }
}
