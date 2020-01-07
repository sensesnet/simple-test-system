package com.sensesnet.dao.test.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.DaoConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.TestProcess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: Test process
 * Standart CRUD operation
 */
public class TestProcessDao extends AbstractDao<TestProcess>
{
    private static final Logger log = LogManager.getLogger(TestProcessDao.class);

    @Override
    public TestProcess getByIdentifier(TestProcess entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_TEST_PROCESS_BY_ID))
        {
            statement.setString(1, entity.getTestProcessId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[" + this.getClass().getName() + "] Test Process has been selected by id: "
                        + entity.getTestProcessId());
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
        return null;
    }

    @Override
    public List<TestProcess> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        LinkedList<TestProcess> testProcessList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_TEST_PROCESS))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    testProcessList.add(this.buildEntity(resultSet));
                }
                log.info("[" + this.getClass().getName() + "] All test processes has been selected.");
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
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().INSERT_NEW_TEST_PROCESS))
            {
                prepareStatementParams(
                        statement,
                        entity.getTestProcessId(),
                        entity.getTestProcessDate(),
                        entity.getUserId(),
                        entity.getTestId(),
                        entity.getMainResultValue(),
                        entity.isCompleted()).execute();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] New test process has been added: " + entity.toString());
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
                throw new DaoException("SQL Error: Have no access to DB.", ex);
            }
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
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().DELETE_TEST_PROCESS_BY_ID))
            {
                statement.setString(1, entity.getTestProcessId());
                statement.execute();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] Test process has been removed: " + entity.toString());
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
                throw new DaoException("SQL Error: Have no access to DB.", ex);
            }
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
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().UPDATE_TEST_PROCESS))
            {
                prepareStatementParams(
                        statement,
                        entity.getTestProcessDate(),
                        entity.getUserId(),
                        entity.getTestId(),
                        entity.getMainResultValue(),
                        entity.isCompleted(),
                        entity.getTestProcessId()).execute();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] Test process has been updated: " + entity.toString());
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
                throw new DaoException("SQL Error: Have no access to DB.", ex);
            }
        }
        finally
        {
            closeConnection(connection);
        }
    }

    public TestProcess getByIdentifier(String testProcessId) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_TEST_PROCESS_BY_ID))
        {
            statement.setString(1, testProcessId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[" + this.getClass().getName() + "] Test Process has been selected by id: "
                        + testProcessId);
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
        return null;
    }

    public List<TestProcess> getHistoryByUserId(Integer userId) throws ConnectionPoolException, DaoException
    {
        LinkedList<TestProcess> testProcessList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_TEST_PROCESS_BY_USER_ID))
        {
            statement.setInt(1,userId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    testProcessList.add(this.buildEntity(resultSet));
                }
                log.info("[" + this.getClass().getName() + "] All test processes has been selected.");
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
    public TestProcess buildEntity(ResultSet resultSet) throws SQLException
    {
        return TestProcess.builder()
                .testProcessId(resultSet.getString("test_process_id"))
                .testProcessDate(resultSet.getString("test_process_date"))
                .userId(resultSet.getInt("user_id"))
                .testId(resultSet.getInt("test_id"))
                .mainResultValue(resultSet.getInt("main_result_value"))
                .isCompleted(resultSet.getBoolean("is_completed")).build();
    }
}
