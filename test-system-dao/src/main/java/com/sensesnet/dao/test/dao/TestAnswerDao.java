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
                if(resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[TestAnswerDao] Answer has been selected by id: " + entity.getAnswerId());
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
                log.info("[TestAnswerDao] All answers has been selected.");
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
                        entity.getQuestionId()).executeUpdate();
                log.info("[TestAnswerDao] Answer has been added: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error("[TestAnswerDao] Test answer has NOT added, DB access error. Error: " + e.getLocalizedMessage());
            try
            {
                connection.rollback();
                log.warn("[TestAnswerDao] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                log.error("[TestAnswerDao] Rollback has NOT possible.");
                throw new DaoException("SQL Error: Have no access to DB.", ex);
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
                statement.execute();
                log.info("[TestAnswerDao] Answer has been removed: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error(
                    "[TestAnswerDao] Test answer has NOT removed, DB access error. Error: " + e.getLocalizedMessage());
            try
            {
                connection.rollback();
                log.warn("[TestAnswerDao] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                log.error("[TestAnswerDao] Rollback has NOT possible.");
                throw new DaoException("SQL Error: Have no access to DB.", ex);
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
                log.info("[TestAnswerDao] Answer has been updated: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error(
                    "[TestAnswerDao] Test answer has NOT updated, DB access error. Error: " + e.getLocalizedMessage());
            try
            {
                connection.rollback();
                log.warn("[TestAnswerDao] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                log.error("[TestAnswerDao] Rollback has NOT possible.");
                throw new DaoException("SQL Error: Have no access to DB.", ex);
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
