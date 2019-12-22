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

    public TestResultDao()
    {
        log.info("[TestResultDao] TestResultDao has been initialized.");
    }

    @Override
    public TestResult getByIdentifier(TestResult entity) throws ConnectionPoolException, DaoException
    {
        LinkedList<TestResult> resultList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_RESULT_BY_ID))
        {
            statement.setInt(1, entity.getResultId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    resultList.add(TestResult.builder()
                            .resultId(resultSet.getInt("result_id"))
                            .testProcessId(resultSet.getInt("test_process_id"))
                            .questionId(resultSet.getInt("question_id"))
                            .answerId(resultSet.getInt("answer_id")).build());
                }
                if (resultList.size() != 1)
                {
                    log.warn("[TestResultDao] Test " + entity.getResultId() + " is not exist!");
                    return null;
                }
                log.info("[TestResultDao] Result has been selected by id: " + entity.getResultId());
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
        return resultList.iterator().next();
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
                    resultList.add(TestResult.builder()
                            .resultId(resultSet.getInt("result_id"))
                            .testProcessId(resultSet.getInt("test_process_id"))
                            .questionId(resultSet.getInt("question_id"))
                            .answerId(resultSet.getInt("answer_id")).build());
                }
                log.info("[TestResultDao] All results has been selected.");
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
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().INSERT_RESULT))
        {
            prepareStatementParams(
                    statement,
                    entity.getTestProcessId(),
                    entity.getQuestionId(),
                    entity.getAnswerId()).executeUpdate();
            log.info("[TestResultDao] New result has been added: " + entity.toString());
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
    public void removeEntity(TestResult entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().DELETE_RESULT_BY_ID))
        {
            statement.setInt(1, entity.getResultId());
            statement.execute();
            log.info("[TestResultDao] Result has been removed: " + entity.toString());
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
    public void editEntity(TestResult entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().UPDATE_RESULT))
        {
            prepareStatementParams(
                    statement,
                    entity.getTestProcessId(),
                    entity.getQuestionId(),
                    entity.getAnswerId(),
                    entity.getResultId()).executeQuery();
            log.info("[TestResultDao] Result has been updated: " + entity.toString());
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
