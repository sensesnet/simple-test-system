package com.sensesnet.dao.test.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.Constant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.Test;
import com.sensesnet.pojo.test.TestProcess;
import com.sensesnet.pojo.test.TestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: Test process
 * Standart CRUD operation
 */
public class TestProcessDao extends AbstractDao<TestProcess>
{

    private static final Logger log = LogManager.getLogger(Test.class);

    public TestProcessDao()
    {
        log.info("[TestProcessDao] TestProcessDao has been initialized.");
    }

    @Override
    public TestProcess getByIdentifier(TestProcess entity) throws ConnectionPoolException, DaoException
    {
        CopyOnWriteArrayList<TestProcess> testProcessList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().SELECT_TEST_PROCESS_BY_ID))
        {
            statement.setInt(1, entity.getTestProcessId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    testProcessList.add(TestProcess.builder()
                            .testProcessId(resultSet.getInt("test_process_id"))
                            .testProcessDate(resultSet.getDate("test_process_date"))
                            .userId(resultSet.getInt("user_id"))
                            .testId(resultSet.getInt("test_id"))
                            .mainResultValue(resultSet.getInt("main_result_value"))
                            .isCompleted(resultSet.getBoolean("is_completed")).build());
                }
                assert testProcessList.size() == 1;
                log.info("[UserDao] Test Process has been selected by id: " + entity.getResultId());
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        catch (AssertionError e)
        {
            log.info("[Auth] Test info by id [" + entity.getResultId() + "] is not exist!");
            return null;
        }
        finally
        {
            closeConnection(connection);
        }
        return testProcessList.iterator().next();
    }

    @Override
    public List<TestProcess> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        CopyOnWriteArrayList<TestProcess> testProcessList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().SELECT_ALL_TEST_PROCESS))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    testProcessList.add(TestProcess.builder()
                            .testProcessId(resultSet.getInt("test_process_id"))
                            .testProcessDate(resultSet.getDate("test_process_date"))
                            .userId(resultSet.getInt("user_id"))
                            .testId(resultSet.getInt("test_id"))
                            .mainResultValue(resultSet.getInt("main_result_value"))
                            .isCompleted(resultSet.getBoolean("is_completed")).build());
                }
                log.info("[UserDao] All test processes has been selected.");
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
        return testProcessList;
    }

    @Override
    public void addEntity(TestProcess entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().INSERT_NEW_TEST_PROCESS))
        {
            prepareStatementParams(
                    statement,
                    entity.getTestProcessDate(),
                    entity.getUserId(),
                    entity.getTestId(),
                    entity.getMainResultValue(),
                    entity.isCompleted()).executeUpdate();
            log.info("[UserDao] New test process has been added: " + entity.toString());
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
    public void removeEntity(TestProcess entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().DELETE_TEST_PROCESS_BY_ID))
        {
            statement.setInt(1, entity.getTestProcessId());
            statement.execute();
            log.info("[UserDao] Test process has been removed: " + entity.toString());
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
    public void editEntity(TestProcess entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().UPDATE_TEST_PROCESS))
        {
            prepareStatementParams(
                    statement,
                    entity.getTestProcessDate(),
                    entity.getUserId(),
                    entity.getTestId(),
                    entity.getMainResultValue(),
                    entity.isCompleted(),
                    entity.getTestProcessId()).executeQuery();
            log.info("[UserDao] Test process has been updated: " + entity.toString());
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
