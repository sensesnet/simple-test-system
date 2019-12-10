package com.sensesnet.command;

import com.sensesnet.command.impl.HomeCommand;
import com.sensesnet.command.impl.SignInCommand;
import com.sensesnet.command.impl.SignUpCommand;

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
        commands.put(CommandType.HOME, new HomeCommand());
    }

    public ICommand getCommand(String name)
    {
        return commands.get(CommandType.valueOf(name.toUpperCase()));
    }

}
