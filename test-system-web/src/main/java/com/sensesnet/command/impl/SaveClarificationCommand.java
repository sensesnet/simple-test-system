package com.sensesnet.command.impl;

import com.sensesnet.ServiceFactory;
import com.sensesnet.TestAnswerService;
import com.sensesnet.TestQuestionService;
import com.sensesnet.command.Command;
import com.sensesnet.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class SaveClarificationCommand implements Command
{
    private static final Logger log = LogManager.getLogger(SaveClarificationCommand.class);
    private TestAnswerService testAnswerService = ServiceFactory.getInstance().getTestAnswerService();
    private TestQuestionService testQuestionService = ServiceFactory.getInstance().getTestQuestionService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException
    {

    }
}
