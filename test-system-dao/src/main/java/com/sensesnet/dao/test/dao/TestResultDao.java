package com.sensesnet.dao.test.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.DaoConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.TestResult;
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
 * DAO: Test result.
 * Standart CRUD operation
 */
public class TestResultDao extends AbstractDao<TestResult>
{
    private static final Logger log = LogManager.getLogger(TestResult.class);

    @Override
    public TestResult getByIdentifier(TestResult entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_RESULT_BY_ID))
        {
            statement.setInt(1, entity.getResultId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[" + this.getClass().getName() + "] Result has been selected by id: " + entity.getResultId());
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
    public List<TestResult> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        LinkedList<TestResult> resultList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_RESULT))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    resultList.add(this.buildEntity(resultSet));
                }
                log.info("[" + this.getClass().getName() + "] All results has been selected.");
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
        return resultList;

    }

    @Override
    public void addEntity(TestResult entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().INSERT_RESULT))
            {
                prepareStatementParams(
                        statement,
                        entity.getTestProcessId(),
                        entity.getQuestionId(),
                        entity.getAnswerId()).execute();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] New result has been added: " + entity.toString());
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
    public void removeEntity(TestResult entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().DELETE_RESULT_BY_ID))
            {
                statement.setInt(1, entity.getResultId());
                statement.execute();
                log.info("[" + this.getClass().getName() + "] Result has been removed: " + entity.toString());
                connection.commit();
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
    public void editEntity(TestResult entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().UPDATE_RESULT))
            {
                prepareStatementParams(
                        statement,
                        entity.getTestProcessId(),
                        entity.getQuestionId(),
                        entity.getAnswerId(),
                        entity.getResultId()).executeQuery();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] Result has been updated: " + entity.toString());
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

    @Override
    public TestResult buildEntity(ResultSet resultSet) throws SQLException
    {
        return TestResult.builder()
                .resultId(resultSet.getInt("result_id"))
                .testProcessId(resultSet.getString("test_process_id"))
                .questionId(resultSet.getInt("question_id"))
                .answerId(resultSet.getInt("answer_id")).build();
    }
}
