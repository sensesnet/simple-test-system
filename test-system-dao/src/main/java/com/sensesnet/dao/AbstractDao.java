package com.sensesnet.dao;

import com.sensesnet.dao.exception.DaoException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Abstract DAO class.
 */
public abstract class AbstractDao<T>
{
    public abstract String getCreateQuery() throws DaoException;
    public abstract String getSelectQuery() throws DaoException;
    public abstract String getUpdateQuery() throws DaoException;
    public abstract String getDeleteQuery() throws DaoException;
}
