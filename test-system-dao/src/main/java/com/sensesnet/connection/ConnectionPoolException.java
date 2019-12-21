package com.sensesnet.connection;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Connection Pool Exception
 */
public class ConnectionPoolException extends Exception
{
    private static final long serialVersionUID = 1L;

    public ConnectionPoolException(String message)
    {
        super(message);
    }

    public ConnectionPoolException(String message, Exception e)
    {
        super(message, e);
    }
}
