package com.sensesnet.dao.test.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.DaoConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public TestDao()
    {
        log.info("[TestDao] TestDao has been initialized.");
    }

    @Override
    public Test getByIdentifier(Test entity) throws ConnectionPoolException, DaoException
    {
        CopyOnWriteArrayList<Test> testList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_TEST_BY_ID))
        {
            statement.setInt(1, entity.getTestId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    testList.add(Test.builder()
                            .testId(resultSet.getInt("test_id"))
                            .testName(resultSet.getString("test_name"))
                            .testDescription(resultSet.getString("test_description"))
                            .testValue(resultSet.getInt("test_value"))
                            .testTime(resultSet.getTime("test_time")).build());
                }
                assert testList.size() == 1;
                log.info("[UserDao] Test has been selected by id: " + entity.getTestId());
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        catch (AssertionError e)
        {
            log.info("[Auth] Test info by id [" + entity.getTestId() + "] is not exist!");
            return null;
        }
        finally
        {
            closeConnection(connection);
        }
        return testList.iterator().next();
    }

    @Override
    public List<Test> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        CopyOnWriteArrayList<Test> testList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_TEST))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    testList.add(Test.builder()
                            .testId(resultSet.getInt("test_id"))
                            .testName(resultSet.getString("test_name"))
                            .testDescription(resultSet.getString("test_description"))
                            .testValue(resultSet.getInt("test_value"))
                            .testTime(resultSet.getTime("test_time")).build());
                }
                log.info("[UserDao] All tests has been selected.");
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
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
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().INSERT_NEW_TEST))
        {
            prepareStatementParams(
                    statement,
                    entity.getTestDescription(),
                    entity.getTestValue(),
                    entity.getTestTime()).executeUpdate();
            log.info("[UserDao] New test has been added: " + entity.toString());
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
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
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().DELETE_TEST_BY_ID))
        {
            statement.setInt(1, entity.getTestId());
            statement.execute();
            log.info("[UserDao] Test has been removed: " + entity.toString());
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
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
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().UPDATE_TEST))
        {
            prepareStatementParams(
                    statement,
                    entity.getTestName(),
                    entity.getTestDescription(),
                    entity.getTestValue(),
                    entity.getTestTime()).executeQuery();
            log.info("[UserDao] Answer has been updated: " + entity.toString());
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        finally
        {
            closeConnection(connection);
        }
    }

}
