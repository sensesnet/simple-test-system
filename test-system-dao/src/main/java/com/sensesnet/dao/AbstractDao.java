package com.sensesnet.dao;

import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.exception.DaoException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Abstract DAO class.
 */
public abstract class AbstractDao<T>
{

    /**
     * Get connection via Connection Pool
     * @return
     * @throws ConnectionPoolException
     */
    protected Connection getConnection() throws ConnectionPoolException
    {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        return connectionPool.takeConnection();
    }

    /**
     * Query generator
     * @return
     * @throws DaoException
     */
    public abstract String getCreateQuery() throws DaoException;
    /**
     * Query generator
     * @return
     * @throws DaoException
     */
    public abstract String getSelectQuery() throws DaoException;
    /**
     * Query generator
     * @return
     * @throws DaoException
     */
    public abstract String getUpdateQuery() throws DaoException;
    /**
     * Query generator
     * @return
     * @throws DaoException
     */
    public abstract String getDeleteQuery() throws DaoException;

    /**
     * Dao
     * - get entity
     * @param entity
     * @return
     */
    public abstract T getByIdentifier(T entity);

    /**
     * Dao
     * - get List<T> of entities
     * @return
     */
    public abstract List<T> getListOfEntity();

    /**
     * Dao
     * - set entity
     * @param entity
     */
    public abstract void addEntity(T entity);

    /**
     *  Dao
     *  - remove Entity
     * @param entity
     */
    public abstract void removeEntity(T entity);

    /**
     * Dao
     * - edit entity
     */
    public abstract void editEntity(T entity);
}
