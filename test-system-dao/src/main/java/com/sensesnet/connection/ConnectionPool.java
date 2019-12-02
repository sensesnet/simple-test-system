package com.sensesnet.connection;

import com.sensesnet.constant.Constant;
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
    private static final ConnectionPool instance = new ConnectionPool();
    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> givenConnections;
    private String databaseServerName = Config.getProperty(Constant.dbConnection().DATABASE_SERVER_NAME);
    private String databasePortNumber = Config.getProperty(Constant.dbConnection().DATABASE_PORT_NUMBER);
    private String databaseName = Config.getProperty(Constant.dbConnection().DATABASE_NAME);
    private String username = Config.getProperty(Constant.dbConnection().DATABASE_USERNAME);
    private String password = Config.getProperty(Constant.dbConnection().DATABASE_PASSWORD);
    private String databaseClass = Config.getProperty(Constant.dbConnection().DATABASE_DB_CLASS);
    private String databaseScheme = Config.getProperty(Constant.dbConnection().DATABASE_SCHEME);
    private String databaseLink =
            "jdbc:mysql://"
                    + databaseServerName + ":"
                    + databasePortNumber + "/" + databaseScheme
                    + "?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static Integer connectionCount = Integer.valueOf(Config.getProperty(Constant.dbConnection().CONNECTION_COUNT));

    public static ConnectionPool getInstance() throws ConnectionPoolException
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
        freeConnections = new ArrayBlockingQueue<>(connectionCount);
        givenConnections = new ArrayBlockingQueue<>(connectionCount);

        try
        {
            Class.forName(databaseClass);
            for (int i = 0; i < connectionCount; i++)
            {
                freeConnections.put(DriverManager.getConnection(databaseLink, username, password));
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
            connection = freeConnections.take();
            givenConnections.add(connection);
        }
        catch (InterruptedException e)
        {
            throw new ConnectionPoolException("Connection pool: blocked thread on take DB connection!", e);
        }
        return connection;
    }

    /**
     * Close all connections
     *
     * @throws ConnectionPoolException
     * @throws SQLException
     */
    public void closeAllConnections() throws ConnectionPoolException, SQLException
    {
        this.closeConnectionQueue(freeConnections);
        this.closeConnectionQueue(givenConnections);
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
        for (int i = 0; i < queue.size(); i++)
        {
            Connection connection = queue.poll();
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
     * Close connection statement and resultSet
     *
     * @param connection
     * @param statement
     * @param resultSet
     */
    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            log.error("[ConnectionPoll] Connection has not returned to the pool.");
            e.printStackTrace();
        }
        try
        {
            resultSet.close();
        }
        catch (SQLException e)
        {
            log.error("[ConnectionPoll] ResultSet has not been closed.");
            e.printStackTrace();
        }
        try
        {
            statement.close();
        }
        catch (SQLException e)
        {
            log.error("[ConnectionPoll] Statement has not been closed.");
            e.printStackTrace();
        }
    }

    /**
     * Close statement and connection
     *
     * @param connection
     * @param statement
     */
    public void closeConnection(Connection connection, Statement statement)
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            log.error("[ConnectionPoll] Connection has not returned to the pool.");
            e.printStackTrace();
        }
        try
        {
            statement.close();
        }
        catch (SQLException e)
        {
            log.error("[ConnectionPoll] Statement has not been closed.");
            e.printStackTrace();
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
            throw new SQLException("Attempting to close closed connection.");
        if (connection.isReadOnly())
            connection.setReadOnly(false);
        if (!givenConnections.remove(connection))
            throw new SQLException("Error deleting connection from the given away pool.");
        if (!freeConnections.offer(connection))
            throw new SQLException("Error allocating connection in the pool.");
    }
}
