package com.sensesnet.util.exception;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Config exception
 */
public class UnknownPropertyException extends RuntimeException
{
    public UnknownPropertyException()
    {
        super();
    }

    public UnknownPropertyException(final String message)
    {
        super(message);
    }
}
