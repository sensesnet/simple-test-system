package com.sensesnet.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Custom Connection pool
 */

public class ConnectionPool implements ConnectionPoolConfig
{
    private static final Logger log = LogManager.getLogger(ConnectionPool.class);
    private static final ConnectionPool instance = new ConnectionPool();
    private BlockingQueue<Connection> freeConnectionsQueue;
    private BlockingQueue<Connection> givenAwayConnectionsQueue;


    public static ConnectionPool getInstance()
    {
        return instance;
    }

    /**
     * Connection queue initialization
     *
     * @throws ConnectionPoolException
     */
    public void initPoolData() throws ConnectionPoolException
    {
        freeConnectionsQueue = new ArrayBlockingQueue<>(connectionCount);
        givenAwayConnectionsQueue = new ArrayBlockingQueue<>(connectionCount);

        try
        {
            Class.forName(databaseClass);
            for (int i = 0; i < connectionCount; i++)
            {
                freeConnectionsQueue.put(DriverManager.getConnection(databaseLink, username, password));
            }
        }
        catch (ClassNotFoundException e)
        {
            throw new ConnectionPoolException("Connection pool: database class not found!", e);
        }
        catch (SQLException e)
        {
            throw new ConnectionPoolException("Connection pool: lose DB connection!", e);
        }
        catch (InterruptedException e)
        {
            throw new ConnectionPoolException("Connection pool: blocked thread on DB connection initialisation!", e);
        }
    }

    /**
     * Get free connection
     *
     * @return
     * @throws ConnectionPoolException
     */
    public Connection takeConnection() throws ConnectionPoolException
    {
        Connection connection;
        try
        {
            connection = freeConnectionsQueue.take();
            givenAwayConnectionsQueue.add(connection);
        }
        catch (InterruptedException e)
        {
            throw new ConnectionPoolException("Connection pool: blocked thread on take DB connection!", e);
        }
        return connection;
    }

    /**
     * Close all connections & destroy connection pool
     *
     * @throws ConnectionPoolException
     * @throws SQLException
     */
    public void destroyConnectionPool() throws ConnectionPoolException, SQLException
    {
        closeConnectionQueue(freeConnectionsQueue);
        closeConnectionQueue(givenAwayConnectionsQueue);
        log.info("[ConnectionPool] All Db connections closed.");
    }

    /**
     * Close connection pool
     *
     * @param queue
     * @throws SQLException
     * @throws ConnectionPoolException
     */
    private void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException, ConnectionPoolException
    {
        Connection connection;
        while ((connection = queue.poll()) != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                throw new ConnectionPoolException("Connection pool: lose DB connection!", e);
            }
        }
    }

    /**
     * Close connection
     *
     * @param connection
     */
    public void closeConnection(Connection connection) throws SQLException
    {
        if (connection.isClosed())
            throw new SQLException("Attempting to close closed connection. Already closed connection.");
        if (connection.isReadOnly())
        {
            log.info("[ConnectionPool] Connection set attribute ['readOnly' : false]");
            connection.setReadOnly(false);
        }
        if (!givenAwayConnectionsQueue.remove(connection))
            throw new SQLException("Error deleting connection from the given away pool.");
        if (!freeConnectionsQueue.offer(connection))
            throw new SQLException("Error allocating connection in the pool.");
    }
}
