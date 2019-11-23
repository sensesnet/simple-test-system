package com.sensesnet.dao.exception;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: Dao exception.
 */

public class DaoException extends Exception
{
    public DaoException(String msg, Exception e)
    {
        super(msg, e);
    }

    public DaoException(Exception e)
    {
        super(e);
    }

    public DaoException(String msg)
    {
        super(msg);
    }
}
