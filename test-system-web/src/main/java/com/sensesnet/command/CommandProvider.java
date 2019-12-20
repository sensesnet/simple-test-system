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
    private Map<CommandType, ICommand> commands = new HashMap<CommandType, ICommand>();

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
    }

    public ICommand getCommand(String name)
    {
        return commands.get(CommandType.valueOf(name.toUpperCase()));
    }

}
