package com.sensesnet.dao.test.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.DaoConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: Test pojo
 * Standart CRUD operation
 */
public class TestDao extends AbstractDao<Test>
{
    private static final Logger log = LogManager.getLogger(Test.class);

    @Override
    public Test getByIdentifier(Test entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_TEST_BY_ID))
        {
            statement.setInt(1, entity.getTestId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[" + this.getClass().getName() + "] Test has been selected by id: " + entity.getTestId());
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("[" + this.getClass().getName() + "] Have no access to DB.", e);
        }
        finally
        {
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<Test> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        LinkedList<Test> testList = new LinkedList<>();
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement())
        {
            try (ResultSet resultSet = statement.executeQuery(DaoConstant.query().SELECT_ALL_TEST))
            {
                while (resultSet.next())
                {
                    testList.add(this.buildEntity(resultSet));
                }
                log.info("[" + this.getClass().getName() + "] All tests has been selected.");
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("[" + this.getClass().getName() + "] Have no access to DB.", e);
        }
        finally
        {
            closeConnection(connection);
        }
        return testList;
    }

    @Override
    public void addEntity(Test entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().INSERT_NEW_TEST))
            {
                prepareStatementParams(
                        statement,
                        entity.getTestDescription(),
                        entity.getTestValue(),
                        entity.getTestTime()).executeUpdate();
                log.info("[" + this.getClass().getName() + "] New test has been added: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error("[" + this.getClass().getName() + "] Test has NOT added, DB access error.", e);
            try
            {
                connection.rollback();
                log.warn("[" + this.getClass().getName() + "] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                throw new DaoException("[" + this.getClass().getName() + "] Have no access to DB.", ex);
            }
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public void removeEntity(Test entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().DELETE_TEST_BY_ID))
            {
                statement.setInt(1, entity.getTestId());
                statement.execute();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] Test has been removed: " + entity.toString());
            }
        }
        catch (SQLException e)
        {
            log.error("[" + this.getClass().getName() + "] Test has NOT removed, DB access error.", e);
            try
            {
                connection.rollback();
                log.warn("[" + this.getClass().getName() + "] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                throw new DaoException("[" + this.getClass().getName() + "] Have no access to DB.", ex);
            }
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public void editEntity(Test entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().UPDATE_TEST))
            {
                prepareStatementParams(
                        statement,
                        entity.getTestName(),
                        entity.getTestDescription(),
                        entity.getTestValue(),
                        entity.getTestTime(),
                        entity.getTestId()).execute();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] Test has been updated: " + entity.toString());
            }
        }
        catch (SQLException e)
        {
            log.error("[" + this.getClass().getName() + "] Test has NOT updated, DB access error.", e);
            try
            {
                connection.rollback();
                log.warn("[" + this.getClass().getName() + "] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                throw new DaoException("[" + this.getClass().getName() + "] Have no access to DB.", ex);
            }
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public Test buildEntity(ResultSet resultSet) throws SQLException
    {
        return Test.builder()
                .testId(resultSet.getInt("test_id"))
                .testName(resultSet.getString("test_name"))
                .testDescription(resultSet.getString("test_description"))
                .testValue(resultSet.getInt("test_value"))
                .testTime(resultSet.getInt("test_time")).build();
    }
}
