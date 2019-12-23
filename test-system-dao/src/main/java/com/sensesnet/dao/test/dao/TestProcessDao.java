package com.sensesnet.dao.test.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.DaoConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.Test;
import com.sensesnet.pojo.test.TestProcess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static final Logger log = LogManager.getLogger(TestProcessDao.class);

    @Override
    public TestProcess getByIdentifier(TestProcess entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_TEST_PROCESS_BY_ID))
        {
            statement.setInt(1, entity.getTestProcessId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[TestProcessDao] Test Process has been selected by id: " + entity.getResultId());
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
                log.info("[TestProcessDao] All test processes has been selected.");
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
                        entity.getTestProcessDate(),
                        entity.getUserId(),
                        entity.getTestId(),
                        entity.getMainResultValue(),
                        entity.isCompleted()).executeUpdate();
                log.info("[TestProcessDao] New test process has been added: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error("[TestProcessDao] Test has NOT added, DB access error. Error: " + e.getLocalizedMessage());
            try
            {
                connection.rollback();
                log.warn("[TestProcessDao] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                log.error("[TestProcessDao] Rollback has NOT possible.");
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
                statement.setInt(1, entity.getTestProcessId());
                statement.execute();
                log.info("[TestProcessDao] Test process has been removed: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error("[TestProcessDao] Test has NOT removed, DB access error. Error: " + e.getLocalizedMessage());
            try
            {
                connection.rollback();
                log.warn("[TestProcessDao] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                log.error("[TestProcessDao] Rollback has NOT possible.");
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
                        entity.getTestProcessId()).executeQuery();
                log.info("[TestProcessDao] Test process has been updated: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error("[TTestProcessDao] Test has NOT updated, DB access error. Error: " + e.getLocalizedMessage());
            try
            {
                connection.rollback();
                log.warn("[TestProcessDao] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                log.error("[TestDao] Rollback has NOT possible.");
                throw new DaoException("SQL Error: Have no access to DB.", ex);
            }
        }
        finally
        {
            closeConnection(connection);
        }
    }

    @Override
    public TestProcess buildEntity(ResultSet resultSet) throws SQLException
    {
        return TestProcess.builder()
                .testProcessId(resultSet.getInt("test_process_id"))
                .testProcessDate(resultSet.getDate("test_process_date"))
                .userId(resultSet.getInt("user_id"))
                .testId(resultSet.getInt("test_id"))
                .mainResultValue(resultSet.getInt("main_result_value"))
                .isCompleted(resultSet.getBoolean("is_completed")).build();
    }
}
