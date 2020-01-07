package com.sensesnet.dao;

import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.authentication.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Abstract DAO class.
 */
public abstract class AbstractDao<T>
{
    private static ConnectionPool connectionPool;
    private static final Logger log = LogManager.getLogger(AbstractDao.class);

    /**
     * Get connection via Connection Pool
     * (Double-checked locking)
     *
     * @return
     */
    protected Connection getConnection() throws ConnectionPoolException
    {
        if (connectionPool == null)
        {
            synchronized (ConnectionPool.class)
            {
                if (connectionPool == null)
                {
                    connectionPool = ConnectionPool.getInstance();
                    connectionPool.initPoolData();
                    log.info("[Abstract DAO] Connection pool has been initialized!");
                }
            }
        }
        return connectionPool.takeConnection();
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
            log.error("SQL exception: Lost DB connection.", e);
        }
        log.info("[Abstract DAO] Connection has been closed with statement!");
    }


    protected PreparedStatement prepareStatementParams(PreparedStatement statement, Object... args) throws SQLException
    {
        int i = 1;
        try
        {
            for (Object arg : args)
            {
                if (arg instanceof Date)
                {
                    statement.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
                }
                else if (arg instanceof Integer)
                {
                    statement.setInt(i++, (Integer) arg);
                }
                else if (arg instanceof Long)
                {
                    statement.setLong(i++, (Long) arg);
                }
                else if (arg instanceof Double)
                {
                    statement.setDouble(i++, (Double) arg);
                }
                else if (arg instanceof Float)
                {
                    statement.setFloat(i++, (Float) arg);
                }
                else if (arg instanceof Boolean)
                {
                    statement.setBoolean(i++, (Boolean) arg);
                }
                else
                {
                    statement.setString(i++, (String) arg);
                }
            }
        }
        catch (SQLException e)
        {
            log.error("[" + this.getClass().getName() + "] Prepared statement has not been prepared", e);
        }
        return statement;
    }

    /**
     * Dao
     * - get impl
     *
     * @param entity
     * @return
     */
    public abstract T getByIdentifier(T entity) throws ConnectionPoolException, DaoException;

    /**
     * Dao
     * - get List<T> of impl
     *
     * @return
     */
    public abstract List<T> getListOfEntity() throws ConnectionPoolException, DaoException;

    /**
     * Dao
     * - set impl
     *
     * @param entity
     */
    public abstract void addEntity(T entity) throws ConnectionPoolException, DaoException;

    /**
     * Dao
     * - remove Entity
     *
     * @param entity
     */
    public abstract void removeEntity(T entity) throws DaoException, ConnectionPoolException;

    /**
     * Dao
     * - edit impl
     */
    public abstract void editEntity(T entity) throws ConnectionPoolException, DaoException;

    /**
     * Dao
     * - build entity
     *
     * @param resultSet
     * @return
     */
    public abstract T buildEntity(ResultSet resultSet) throws SQLException;
}
