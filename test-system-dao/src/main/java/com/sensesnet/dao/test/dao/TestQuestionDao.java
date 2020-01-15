package com.sensesnet.dao.test.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.DaoConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.Test;
import com.sensesnet.pojo.test.TestQuestion;
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
 * DAO: Test question.
 * Standart CRUD operation
 */
public class TestQuestionDao extends AbstractDao<TestQuestion>
{
    private static final Logger log = LogManager.getLogger(TestQuestionDao.class);

    @Override
    public TestQuestion getByIdentifier(TestQuestion entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_QUESTION_BY_ID))
        {
            statement.setInt(1, entity.getQuestionId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[" + this.getClass().getName() + "] Question has been selected by id: "
                        + entity.getQuestionId());
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
    public List<TestQuestion> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        LinkedList<TestQuestion> testQuestionList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_QUESTION))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    testQuestionList.add(this.buildEntity(resultSet));
                }
                log.info("[" + this.getClass().getName() + "] All questions has been selected.");
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
        return testQuestionList;
    }

    @Override
    public void addEntity(TestQuestion entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().INSERT_NEW_QUESTION))
            {
                prepareStatementParams(
                        statement,
                        entity.getQuestionDesc(),
                        entity.getQuestionValue(),
                        entity.getTestId(),
                        entity.getAnswerId(),
                        entity.getQuestionClarification()).executeUpdate();
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
    public void removeEntity(TestQuestion entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().DELETE_QUESTION_BY_ID))
            {
                statement.setInt(1, entity.getQuestionId());
                statement.execute();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] Question has been removed: " + entity.toString());
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
    public void editEntity(TestQuestion entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().UPDATE_QUESTION))
            {
                prepareStatementParams(
                        statement,
                        entity.getQuestionDesc(),
                        entity.getQuestionValue(),
                        entity.getTestId(),
                        entity.getAnswerId(),
                        entity.getQuestionClarification(),
                        entity.getQuestionId()).execute();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] Question has been updated: " + entity.toString());
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

    public List<TestQuestion> listOfQuestionsByTestId(Integer testId) throws ConnectionPoolException, DaoException
    {
        LinkedList<TestQuestion> testQuestionList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_QUESTIONS_BY_TEST_ID))
        {
            statement.setInt(1, testId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    testQuestionList.add(this.buildEntity(resultSet));
                }
                log.info("[" + this.getClass().getName() + "] All questions has been selected.");
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
        return testQuestionList;
    }

    public TestQuestion getQuestionsById(Integer questionId) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_QUESTION_BY_ID))
        {
            statement.setInt(1, questionId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[" + this.getClass().getName() + "] Question has been selected by id: "
                        + questionId);
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

    /**
     * build question entity
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    @Override
    public TestQuestion buildEntity(ResultSet resultSet) throws SQLException
    {
        return TestQuestion.builder()
                .questionId(resultSet.getInt("question_id"))
                .questionDesc(resultSet.getString("question_description"))
                .questionValue(resultSet.getInt("question_value"))
                .testId(resultSet.getInt("test_id"))
                .answerId(resultSet.getInt("answer_id"))
                .questionClarification(resultSet.getString("question_clarification")).build();
    }
}
