package com.sensesnet.comand;

import com.sensesnet.comand.entity.SignInCommand;
import com.sensesnet.comand.entity.SignUpCommand;

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
    }

    public ICommand getCommand(String name)
    {
        return commands.get(CommandType.valueOf(name.toUpperCase()));
    }

}
