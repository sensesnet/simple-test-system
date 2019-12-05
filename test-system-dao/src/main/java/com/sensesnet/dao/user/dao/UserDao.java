package com.sensesnet.dao.user.dao;

import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.Constant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.authentication.User;
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
 * DAO: User
 * Standart CRUD operation
 */
public class UserDao extends AbstractDao<User>
{

    private static final Logger log = LogManager.getLogger(UserDao.class);

    public UserDao()
    {
        log.info("[UserDao] UserDao has been initialized.");
    }

    @Override
    public User getByIdentifier(User entity) throws ConnectionPoolException, DaoException
    {
        CopyOnWriteArrayList<User> userList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().SELECT_USER_BY_ID))
        {
            statement.setInt(1, entity.getUserId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    userList.add(User.builder()
                            .userId(resultSet.getInt("user_id"))
                            .userLogin(resultSet.getString("user_login"))
                            .userPassword(resultSet.getString("user_password"))
                            .userRole(resultSet.getInt("role_id"))
                            .userInfo(resultSet.getInt("info_id")).build());
                }
                assert userList.size() == 1;
                log.info("[UserDao] Account has been selected by id: " + entity.getUserId());
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        catch (AssertionError e)
        {
            log.info("[Auth] User id [" + entity.getUserId() + "] account is not exist!");
            return null;
        }
        finally
        {
            closeConnection(connection);
        }
        return userList.iterator().next();
    }

    @Override
    public List<User> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        CopyOnWriteArrayList<User> userList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().SELECT_ALL_USER))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    userList.add(User.builder()
                            .userId(resultSet.getInt("user_id"))
                            .userLogin(resultSet.getString("user_login"))
                            .userPassword(resultSet.getString("user_password"))
                            .userRole(resultSet.getInt("role_id"))
                            .userInfo(resultSet.getInt("info_id")).build());
                }
                log.info("[UserDao] Account's has been selected.");
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
        return userList;
    }

    @Override
    public void addEntity(User entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().INSERT_NEW_USER))
        {
            prepareStatementParams(
                    statement,
                    entity.getUserId(),
                    entity.getUserPassword(),
                    entity.getUserRole(),
                    entity.getUserInfo()).executeUpdate();
            log.info("[UserDao] Account has been added: " + entity.toString());
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
    public void removeEntity(User entity) throws DaoException, ConnectionPoolException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().DELETE_USER_BY_ID))
        {
            statement.setInt(1, entity.getUserId());
            statement.execute();
            log.info("[UserDao] Account has been removed: " + entity.toString());
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
    public void editEntity(User entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().UPDATE_USER))
        {
            prepareStatementParams(
                    statement,
                    entity.getUserLogin(),
                    entity.getUserPassword(),
                    entity.getUserInfo(),
                    entity.getUserRole(),
                    entity.getUserInfo()).executeQuery();
            log.info("[UserDao] Account has been updated: " + entity.toString());
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

    public User getUserByLoginAndPassword(String login, String password) throws DaoException, ConnectionPoolException
    {
        CopyOnWriteArrayList<User> userList = new CopyOnWriteArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().SELECT_USER_BY_LOGIN_AND_PASSWORD))
        {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    userList.add(User.builder()
                            .userId(resultSet.getInt("user_id"))
                            .userLogin(resultSet.getString("user_login"))
                            .userPassword(resultSet.getString("user_password"))
                            .userRole(resultSet.getInt("role_id"))
                            .userInfo(resultSet.getInt("info_id")).build());
                }
                assert userList.size() == 1;
                log.info("[UserDao] Account has been found: " + userList.get(0).toString());
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        catch (AssertionError e)
        {
            log.info("[Auth] User [" + login + ";" + password + "] account is not exist!");
            return null;
        }
        finally
        {
            closeConnection(connection);
        }
        return userList.iterator().next();
    }
}
