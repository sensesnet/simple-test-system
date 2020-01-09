package com.sensesnet.dao.test.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.DaoConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.TestAnswer;
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
 * DAO: Test answer
 * Standart CRUD operation
 */
public class TestAnswerDao extends AbstractDao<TestAnswer>
{
    private static final Logger log = LogManager.getLogger(TestAnswer.class);

    @Override
    public TestAnswer getByIdentifier(TestAnswer entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ANSWER_BY_ID))
        {
            statement.setInt(1, entity.getAnswerId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[" + this.getClass().getName() + "] Answer has been selected by id: " + entity.getAnswerId());
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
    public List<TestAnswer> getListOfEntity() throws DaoException, ConnectionPoolException
    {
        LinkedList<TestAnswer> testAnswerList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_ANSWER))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    testAnswerList.add(this.buildEntity(resultSet));
                }
                log.info("[" + this.getClass().getName() + "] All answers has been selected.");
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
        return testAnswerList;
    }

    @Override
    public void addEntity(TestAnswer entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().INSERT_NEW_ANSWER))
            {
                prepareStatementParams(
                        statement,
                        entity.getAnswerDescription(),
                        entity.getQuestionId()).executeQuery();
                log.info("[" + this.getClass().getName() + "] Answer has been added: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
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
    public void removeEntity(TestAnswer entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().DELETE_ANSWER_BY_ID))
            {
                statement.setInt(1, entity.getAnswerId());
                statement.executeQuery();
                log.info("[" + this.getClass().getName() + "] Answer has been removed: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
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
    public void editEntity(TestAnswer entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().UPDATE_ANSWER))
            {
                prepareStatementParams(
                        statement,
                        entity.getAnswerDescription(),
                        entity.getQuestionId(),
                        entity.getAnswerId()).executeQuery();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] Answer has been updated: " + entity.toString());
            }
        }
        catch (SQLException e)
        {
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

    public List<TestAnswer> listOfAnswersByQuestionId(Integer questionId) throws ConnectionPoolException, DaoException
    {
        LinkedList<TestAnswer> testAnswerList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_ANSWER_BY_QUESTION_ID))
        {
            statement.setInt(1, questionId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    testAnswerList.add(this.buildEntity(resultSet));
                }
                log.info("[" + this.getClass().getName() + "] All answers for question "
                        + "[" + questionId + "] has been selected.");
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
        return testAnswerList;
    }

    public void removeAnswerById(Integer answerId) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().DELETE_ANSWER_BY_ID))
            {
                statement.setInt(1, answerId);
                statement.execute();
                log.info("[" + this.getClass().getName() + "] Answer has been removed: " + answerId);
                connection.commit();
            }
        }
        catch (SQLException e)
        {
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
    public TestAnswer buildEntity(ResultSet resultSet) throws SQLException
    {
        return TestAnswer.builder()
                .answerId(resultSet.getInt("answer_id"))
                .answerDescription(resultSet.getString("answer_description"))
                .questionId(resultSet.getInt("question_id")).build();
    }
}
