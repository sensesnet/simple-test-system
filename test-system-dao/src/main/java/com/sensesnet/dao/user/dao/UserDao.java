package com.sensesnet.dao.user.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.DaoConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.authentication.User;
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
 * DAO: User
 * Standart CRUD operation
 */
public class UserDao extends AbstractDao<User>
{

    private static final Logger log = LogManager.getLogger(UserDao.class);

    public User getUserByLoginAndPassword(String login, String password) throws
            DaoException, ConnectionPoolException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_BY_LOGIN_AND_PASSWORD))
        {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[UserDao] Account has been found by login and password: [" + login + ";" + password + "].");
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

    public User getUserByLogin(String login) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_BY_LOGIN))
        {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[UserDao] Account has been found by login : " + login);
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

    public User getByIdentifier(Integer userId) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_BY_ID))
        {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[UserDao] Account has been found by id: " + userId);
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
    public User getByIdentifier(User entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_BY_ID))
        {
            statement.setInt(1, entity.getUserId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[UserDao] Account has been found: " + entity.toString());
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
    public List<User> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        LinkedList<User> userList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_USER))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    userList.add(this.buildEntity(resultSet));
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
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().INSERT_NEW_USER))
            {
                prepareStatementParams(
                        statement,
                        entity.getUserLogin(),
                        entity.getUserPassword(),
                        entity.getUserRole(),
                        entity.getUserName(),
                        entity.getUserSurname(),
                        entity.getUserAddress(),
                        entity.getUserBirthday(),
                        entity.getUserPhone()).execute();
                log.info("[UserDao] Account Info has been added: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error("[UserDao] Account's have NOT added, DB access error. Error: " + e.getLocalizedMessage());
            try
            {
                connection.rollback();
                log.warn("[UserDao] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                log.error("[UserDao] Rollback has NOT possible.");
                throw new DaoException("SQL Error: Have no access to DB.", ex);
            }
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
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().DELETE_USER_BY_ID))
            {
                statement.setInt(1, entity.getUserId());
                statement.execute();
                log.info("[UserDao] Account has been removed: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error("[UserDao] Account has NOT removed, DB access error. Error: " + e.getLocalizedMessage());
            try
            {
                connection.rollback();
                log.warn("[UserDao] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                log.error("[UserDao] Rollback has NOT possible.");
                throw new DaoException("SQL Error: Have no access to DB.", ex);
            }
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
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().UPDATE_USER))
            {
                prepareStatementParams(
                        statement,
                        entity.getUserLogin(),
                        entity.getUserPassword(),
                        entity.getUserRole(),
                        entity.getUserName(),
                        entity.getUserSurname(),
                        entity.getUserAddress(),
                        entity.getUserAddress(),
                        entity.getUserBirthday(),
                        entity.getUserPhone(),
                        entity.getUserId()).execute();
                log.info("[UserDao] Account has been updated: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error("[UserDao] Account's have NOT updated, DB access error. Error: " + e.getLocalizedMessage());
            try
            {
                connection.rollback();
                log.warn("[UserDao] Transaction rollback is completed.");
            }
            catch (SQLException ex)
            {
                log.error("[UserDao] Rollback has NOT possible.");
                throw new DaoException("SQL Error: Have no access to DB.", ex);
            }
        }
        finally
        {
            closeConnection(connection);
        }
    }

    /**
     * build User entity
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public User buildEntity(ResultSet resultSet) throws SQLException
    {
        return User.builder()
                .userId(resultSet.getInt("user_id"))
                .userLogin(resultSet.getString("user_login"))
                .userPassword(resultSet.getString("user_password"))
                .userRole(resultSet.getInt("role_id"))
                .userName(resultSet.getString("user_name"))
                .userSurname(resultSet.getString("user_surname"))
                .userAddress(resultSet.getString("user_address"))
                .userBirthday(resultSet.getString("user_birthday"))
                .userPhone(resultSet.getString("user_phone")).build();
    }
}
