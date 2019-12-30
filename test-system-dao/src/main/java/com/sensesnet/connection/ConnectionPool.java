package com.sensesnet.connection;

import com.sensesnet.constant.DaoConstant;
import com.sensesnet.util.Config;
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

public class ConnectionPool
{
    private static final Logger log = LogManager.getLogger(ConnectionPool.class);

    private final String databaseServerName = Config.getProperty(DaoConstant.dbConnection().DATABASE_SERVER_NAME);
    private final String databasePortNumber = Config.getProperty(DaoConstant.dbConnection().DATABASE_PORT_NUMBER);
    private final String databaseName = Config.getProperty(DaoConstant.dbConnection().DATABASE_NAME);

    private final String username = Config.getProperty(DaoConstant.dbConnection().DATABASE_USERNAME);
    private final String password = Config.getProperty(DaoConstant.dbConnection().DATABASE_PASSWORD);

    private final String databaseClass = Config.getProperty(DaoConstant.dbConnection().DATABASE_DB_CLASS);
    private final String databaseScheme = Config.getProperty(DaoConstant.dbConnection().DATABASE_SCHEME);
    private final String databaseLink =
            "jdbc:mysql://"
                    + databaseServerName + ":"
                    + databasePortNumber + "/" + databaseScheme
                    + "?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private final Integer connectionCount = 20;

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
            throw new ConnectionPoolException("[" + this.getClass().getName() + "]: database class not found!", e);
        }
        catch (SQLException e)
        {
            throw new ConnectionPoolException("[" + this.getClass().getName() + "]: lose DB connection!", e);
        }
        catch (InterruptedException e)
        {
            throw new ConnectionPoolException("[" + this.getClass().getName() + "]: "
                    + "blocked thread on DB connection initialisation!", e);
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
            throw new ConnectionPoolException(
                    "[" + this.getClass().getName() + "]: blocked thread on take DB connection!", e);
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
        log.info("[" + this.getClass().getName() + "] All Db connections closed.");
    }

    /**
     * Close connection pool
     *
     * @param queue
     * @throws SQLException
     * @throws ConnectionPoolException
     */
    private void closeConnectionQueue(BlockingQueue<Connection> queue) throws ConnectionPoolException
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
                throw new ConnectionPoolException("[" + this.getClass().getName() + "]: lose DB connection!", e);
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
        if (!connection.getAutoCommit())
        {
            connection.setAutoCommit(true);
            log.info("Connection set attribute ['autoCommit' : true]");
        }
        if (connection.isReadOnly())
        {
            connection.setReadOnly(false);
            log.info("Connection set attribute ['readOnly' : false]");
        }
        if (!givenAwayConnectionsQueue.remove(connection))
            throw new SQLException("Error deleting connection from the given away pool.");
        if (!freeConnectionsQueue.offer(connection))
            throw new SQLException("Error allocating connection in the pool.");
    }
}
