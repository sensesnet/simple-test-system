package com.sensesnet.dao;

import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Abstract DAO class.
 */
public abstract class AbstractDao<T>
{
    private ConnectionPool connectionPool = null;
    private static final Logger log = LogManager.getLogger(AbstractDao.class);

    /**
     * Get connection via Connection Pool
     *
     * @return
     * @throws ConnectionPoolException
     */
    protected Connection getConnection() throws ConnectionPoolException
    {
        if (connectionPool == null)
        {
            connectionPool = ConnectionPool.getInstance();
            connectionPool.initPoolData();
            log.info("[Abstract DAO] Connection pool has been initialized!");
        }
        return connectionPool.takeConnection();
    }

    /**
     * Close connection with connection, statement, resultSet
     *
     * @param connection
     * @param statement
     * @param resultSet
     */
    protected void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
    {
        connectionPool.closeConnection(connection, statement, resultSet);
        log.info("[Abstract DAO] Connection has been closed with statement and resultSet!");
    }

    /**
     * Close connection with connection, statement
     *
     * @param connection
     * @param statement
     */
    protected void closeConnection(Connection connection, Statement statement)
    {
        connectionPool.closeConnection(connection, statement);
        log.info("[Abstract DAO] Connection has been closed with statement!");
    }

    /**
     * Close connection with connection, statement, resultSet
     *
     * @param connection
     */
    protected void closeConnection(Connection connection)
    {
        try
        {
            connectionPool.closeConnection(connection);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        log.info("[Abstract DAO] Connection has been closed with statement!");
    }

    /**
     * Query generator
     *
     * @return
     * @throws DaoException
     */
    public abstract String getCreateQuery() throws DaoException;

    /**
     * Query generator
     *
     * @return
     * @throws DaoException
     */
    public abstract String getSelectQuery() throws DaoException;

    /**
     * Query generator
     *
     * @return
     * @throws DaoException
     */
    public abstract String getUpdateQuery() throws DaoException;

    /**
     * Query generator
     *
     * @return
     * @throws DaoException
     */
    public abstract String getDeleteQuery() throws DaoException;

    /**
     * Dao
     * - get entity
     *
     * @param entity
     * @return
     */
    public abstract T getByIdentifier(T entity);

    /**
     * Dao
     * - get List<T> of entities
     *
     * @return
     */
    public abstract List<T> getListOfEntity();

    /**
     * Dao
     * - set entity
     *
     * @param entity
     */
    public abstract void addEntity(T entity);

    /**
     * Dao
     * - remove Entity
     *
     * @param entity
     */
    public abstract void removeEntity(T entity);

    /**
     * Dao
     * - edit entity
     */
    public abstract void editEntity(T entity);
}
