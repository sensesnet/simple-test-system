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
    private String databaseServerName = Config.getProperty(Constant.dbConection().DATABASE_SERVER_NANE);
    private String databasePortNumber = Config.getProperty(Constant.dbConection().DATABASE_PORT_NUMBER);
    private String databaseName = Config.getProperty(Constant.dbConection().DATABASE_NANE);
    private String username = Config.getProperty(Constant.dbConection().DATABASE_USERNAME);
    private String password = Config.getProperty(Constant.dbConection().DATABASE_PASSWORD);
    private String databaseClass = Config.getProperty(Constant.dbConection().DATABASE_DB_CLASS);
    private String databaseScheme = Config.getProperty(Constant.dbConection().DATABASE_SCHEME);
    private String databaseLink =
            "jdbc:mysql://"
                    + databaseServerName + ":"
                    + databasePortNumber + "/" + databaseScheme
                    + "?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static Integer connectionCount = Integer.valueOf(Config.getProperty(Constant.dbConection().CONNECTION_COUNT));

    public static ConnectionPool getInstance()
    {
        return instance;
    }


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

    public void returnConnection(Connection connection) throws ConnectionPoolException
    {
        try
        {
            connection.setAutoCommit(true);
            givenConnections.remove(connection);
            freeConnections.put(connection);
        }
        catch (SQLException | InterruptedException e)
        {
            throw new ConnectionPoolException("exception", e);
        }

    }


    /**
     * Close all connections
     * @throws ConnectionPoolException
     * @throws SQLException
     */
    public void closeAllConnections() throws ConnectionPoolException, SQLException
    {
        this.closeConnectionQueue(freeConnections);
        this.closeConnectionQueue(givenConnections);
        log.info("[ConnectionPool] All Db connections closed.");
    }

    private void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException, ConnectionPoolException
    {
        for (int i = 0; i < queue.size(); i++)
        {
            Connection connection = queue.poll();
            if (connection != null)
                connection.commit();
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


    public static void main(String[] args) throws ConnectionPoolException, SQLException
    {
        ConnectionPool pool = new ConnectionPool();
        pool.initPoolData();
        Connection conection = pool.takeConnection();
        pool.closeAllConnections();
    }
}
