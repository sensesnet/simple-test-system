package com.sensesnet.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
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

    private static final ConnectionPool instance = new ConnectionPool();

    private static final String DRIVER = "db.driver";
    private static final String JDBC_URL = "db.jdbc.url";
    private static final String DB_LOGIN = "db.login";
    private static final String DB_PASSWORD = "db.password";
    private static final String CONNECTION_COUNT = "db.connection_count";

    private static final String DB_PROPERTIES_FILE = "resources.db";

    private static final int MINIMAL_CONNECTION_COUNT = 5;

    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> givenConnections;

    public ConnectionPool()
    {
    }

    public static ConnectionPool getInstance()
    {
        return instance;
    }

    public void init() throws ConnectionPoolException
    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_PROPERTIES_FILE);

        String driver = resourceBundle.getString(DRIVER);
        String jdbcUrl = resourceBundle.getString(JDBC_URL);
        String login = resourceBundle.getString(DB_LOGIN);
        String password = resourceBundle.getString(DB_PASSWORD);

        int connectionCount = 0;
        try
        {
            connectionCount = Integer.parseInt(resourceBundle.getString(CONNECTION_COUNT));
        }
        catch (NumberFormatException e)
        {
            // logging
            connectionCount = MINIMAL_CONNECTION_COUNT;
        }
        freeConnections = new ArrayBlockingQueue<>(connectionCount);
        givenConnections = new ArrayBlockingQueue<>(connectionCount);

        try
        {
            Class.forName(driver);

            Connection connection = DriverManager.getConnection(jdbcUrl, login, password);

            freeConnections.put(connection);

        }
        catch (ClassNotFoundException e)
        {
            throw new ConnectionPoolException("db.prorerties eroor", e);
        }
        catch (SQLException e)
        {
            throw new ConnectionPoolException("connection eroor", e);
        }
        catch (InterruptedException e)
        {
            throw new ConnectionPoolException("pool eroor", e);
        }
    }

    public Connection takeConnection() throws ConnectionPoolException
    {
        Connection connection;

        try
        {
            connection = freeConnections.take();
            givenConnections.put(connection);
        }
        catch (InterruptedException e)
        {
            throw new ConnectionPoolException("take connection error", e);
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

    public void destroyPool() throws ConnectionPoolException
    {
        // int closeConnectionCounter = 0;

        for (int i = 0; i < freeConnections.size(); i++)
        {
            Connection con = freeConnections.poll();
            try
            {
                con.close();
            }
            catch (SQLException e)
            {
                throw new ConnectionPoolException("exception", e);
            }
        }

        for (int i = 0; i < givenConnections.size(); i++)
        {
            Connection con = givenConnections.poll();
            try
            {
                con.close();
            }
            catch (SQLException e)
            {
                throw new ConnectionPoolException("exception", e);
            }
        }

    }

    public static void main(String[] args) throws ConnectionPoolException
    {
        ConnectionPool pool = new ConnectionPool();
        pool.init();
        Connection conection = pool.takeConnection();
        pool.destroyPool();
    }
}
