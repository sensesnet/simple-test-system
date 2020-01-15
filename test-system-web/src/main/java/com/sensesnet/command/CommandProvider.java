package com.sensesnet.command;

import com.sensesnet.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Command provider
 */
public class CommandProvider
{
    private static final Logger log = LogManager.getLogger(CommandProvider.class);
    private Map<CommandType, Command> commands = new HashMap<CommandType, Command>();

    public CommandProvider()
    {
        commands.put(CommandType.SIGN_IN, new SignInCommand());
        commands.put(CommandType.SIGN_UP, new SignUpCommand());
        commands.put(CommandType.CLOSE_SESSION, new CloseSessionCommand());
        commands.put(CommandType.HOME, new HomeCommand());

        commands.put(CommandType.USER_VIEW, new UserViewCommand());
        commands.put(CommandType.USER_ADD, new UserAddCommand());
        commands.put(CommandType.USER_EDIT, new UserEditCommand());
        commands.put(CommandType.USER_REMOVE, new UserRemoveCommand());
        commands.put(CommandType.USER_EDIT_SAVE, new UserSaveEditCommand());
        commands.put(CommandType.USER_NEW_SAVE, new UserSaveNewCommand());
        commands.put(CommandType.CHANGE_PASSWORD, new ChangePasswordCommand());

        commands.put(CommandType.TEST_VIEW, new TestViewCommand());
        commands.put(CommandType.RESULT_VIEW, new TestResultCommand());
        commands.put(CommandType.TEST_PROCESS, new TestProcessCommand());
        commands.put(CommandType.TEST_EDIT, new TestEditCommand());
        commands.put(CommandType.TEST_CREATE, new TestCreateCommand());
        commands.put(CommandType.INVESTIGATE_TEST, new TestInvestigateCommand());

        commands.put(CommandType.START_TEST, new TestProcessCommand());
        commands.put(CommandType.FINISH_TEST, new TestFinishCommand());

        commands.put(CommandType.EDIT_QUESTION, new EditQuestionCommand());
        commands.put(CommandType.EDIT_ANSWERS, new EditAnswersCommand());
        commands.put(CommandType.EDIT_CLARIFICATION, new EditClarificationCommand());

        commands.put(CommandType.SAVE_QUESTION, new SaveQuestionCommand());
        commands.put(CommandType.ADD_ANSWER, new AddAnswerCommand());
        commands.put(CommandType.REMOVE_ANSWER, new RemoveAnswerCommand());
        commands.put(CommandType.SAVE_CLARIFICATION, new SaveClarificationCommand());

        commands.put(CommandType.CREATE_NEW_TEST, new CreateNewTestCommand());
        commands.put(CommandType.CREATE_NEW_QUESTION, new CreateNewQuestionCommand());

    }

    /**
     * Mapping Action to Command
     *
     * @param request
     * @return
     */
    public Command getCommand(HttpServletRequest request)
    {
        String action = request.getParameter("action").toUpperCase();
        if (action == null)
        {
            action = CommandType.HOME.toString();
        }
        log.info("[Controller] Command by action:[" + action + "]");
        return commands.get(CommandType.valueOf(action));
    }
}
