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

    public TestAnswerDao()
    {
        log.info("[TestAnswerDao] TestAnswerDao has been initialized.");
    }

    @Override
    public TestAnswer getByIdentifier(TestAnswer entity) throws ConnectionPoolException, DaoException
    {
        CopyOnWriteArrayList<TestAnswer> userList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ANSWER_BY_ID))
        {
            statement.setInt(1, entity.getAnswerId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    userList.add(TestAnswer.builder()
                            .answerId(resultSet.getInt("answer_id"))
                            .answerDescription(resultSet.getString("answer_description"))
                            .questionId(resultSet.getInt("question_id")).build());
                }
                assert userList.size() == 1;
                log.info("[UserDao] Answer has been selected by id: " + entity.getAnswerId());
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        catch (AssertionError e)
        {
            log.info("[Auth] Answer info by id [" + entity.getAnswerId() + "] is not exist!");
            return null;
        }
        finally
        {
            closeConnection(connection);
        }
        return userList.iterator().next();
    }

    @Override
    public List<TestAnswer> getListOfEntity() throws DaoException, ConnectionPoolException
    {
        CopyOnWriteArrayList<TestAnswer> testAnswerList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_ANSWER))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    testAnswerList.add(TestAnswer.builder()
                            .answerId(resultSet.getInt("answer_id"))
                            .answerDescription(resultSet.getString("answer_description"))
                            .questionId(resultSet.getInt("question_id")).build());
                }
                log.info("[UserDao] All answers has been selected.");
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
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().INSERT_NEW_ANSWER))
        {
            prepareStatementParams(
                    statement,
                    entity.getAnswerDescription(),
                    entity.getQuestionId()).executeUpdate();
            log.info("[UserDao] Answer has been added: " + entity.toString());
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
    public void removeEntity(TestAnswer entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().DELETE_ANSWER_BY_ID))
        {
            statement.setInt(1, entity.getAnswerId());
            statement.execute();
            log.info("[UserDao] Answer has been removed: " + entity.toString());
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
    public void editEntity(TestAnswer entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().UPDATE_ANSWER))
        {
            prepareStatementParams(
                    statement,
                    entity.getAnswerDescription(),
                    entity.getQuestionId(),
                    entity.getAnswerId()).executeQuery();
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
