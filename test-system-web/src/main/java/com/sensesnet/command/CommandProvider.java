package com.sensesnet.command;

import com.sensesnet.command.impl.*;

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

        commands.put(CommandType.TEST_VIEW, new TestViewCommand());
        commands.put(CommandType.RESULT_VIEW, new TestResultCommand());
        commands.put(CommandType.TEST_PROCESS, new TestProcessCommand());
        commands.put(CommandType.TEST_EDIT, new TestEditCommand());
        commands.put(CommandType.TEST_CREATE, new TestCreateCommand());

        commands.put(CommandType.START_TEST, new TestProcessCommand());
        commands.put(CommandType.FINISH_TEST, new TestFinishCommand());
    }

    public Command getCommand(String name)
    {
        return commands.get(CommandType.valueOf(name.toUpperCase()));
    }
}
