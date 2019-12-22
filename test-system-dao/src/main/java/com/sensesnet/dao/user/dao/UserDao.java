package com.sensesnet.dao.user.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.DaoConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dto.UserDto;
import com.sensesnet.pojo.authentication.User;
import com.sensesnet.pojo.authentication.UserInfo;
import com.sensesnet.pojo.authentication.UserRole;
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
public class UserDao extends AbstractDao<UserDto>
{

    private static final Logger log = LogManager.getLogger(UserDao.class);

    public UserDao()
    {
        log.info("[UserDao] UserDao has been initialized.");
    }

    @Override
    public UserDto getByIdentifier(UserDto entity) throws ConnectionPoolException, DaoException
    {
        LinkedList<UserDto> userList = new LinkedList<>();
        Connection connection = getConnection();

        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_BY_ID))
        {
            statement.setInt(1, entity.getUser().getUserId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    userList.add(this.getUserDto(resultSet));
                }
                if (userList.size() != 1)
                {
                    log.info("[UserDao] User [" + entity.getUser().getUserLogin() + "] account is not exist!");
                    return null;
                }
                log.info("[UserDao] Account has been found: " + userList.get(0).toString());
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
        return userList.iterator().next();
    }


    @Override
    public List<UserDto> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        LinkedList<UserDto> userList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_USER))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    userList.add(this.getUserDto(resultSet));
                }
                log.info("[UserDao] Account's has been selected.");
                connection.commit();
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
    public void addEntity(UserDto entity) throws ConnectionPoolException, DaoException
    {
        LinkedList<UserInfo> userInfoList = new LinkedList<>();
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().INSERT_NEW_USER_INFO))
            {
                prepareStatementParams(
                        statement,
                        entity.getUserInfo().getUserName(),
                        entity.getUserInfo().getUserSurname(),
                        entity.getUserInfo().getUserAddress(),
                        entity.getUserInfo().getUserBirthday(),
                        entity.getUserInfo().getUserPhone()).execute();
                log.info("[UserDao] Account Info has been added: " + entity.toString());
                connection.commit();
            }
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().INSERT_NEW_USER))
            {
                prepareStatementParams(
                        statement,
                        entity.getUser().getUserLogin(),
                        entity.getUser().getUserPassword(),
                        entity.getUserRole().getRoleId()).execute();
                log.info("[UserDao] Account has been added: " + entity.toString());
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
    public void removeEntity(UserDto entity) throws DaoException, ConnectionPoolException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().DELETE_USER_BY_ID))
            {
                statement.setInt(1, entity.getUser().getUserId());
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
    public void editEntity(UserDto entity) throws ConnectionPoolException, DaoException
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
                        entity.getUser().getUserLogin(),
                        entity.getUser().getUserPassword(),
                        entity.getUserRole().getRoleName(),
                        entity.getUser().getUserId()).execute();
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

    public User getUserByLoginAndPassword(String login, String password) throws
            DaoException, ConnectionPoolException
    {
        LinkedList<User> userList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_BY_LOGIN_AND_PASSWORD))
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
                if (userList.size() != 1)
                {
                    log.info("[UserDao] User [" + login + ";" + password + "] account is not exist!");
                    return null;
                }
                log.info("[UserDao] Account has been found: " + userList.get(0).toString());
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
        return userList.iterator().next();
    }

    public User getUserByLoginAndPassword(String login) throws ConnectionPoolException, DaoException
    {
        LinkedList<User> userList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_BY_LOGIN))
        {
            statement.setString(1, login);
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
                if (userList.size() != 1)
                {
                    log.warn("[UserDao] User [" + login + "] account is not exist!");
                    return null;
                }
                log.info("[UserDao] Account has been found: " + userList.get(0).toString());
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
        return userList.iterator().next();
    }

    public UserDto getByIdentifier(Integer userId) throws ConnectionPoolException, DaoException
    {
        LinkedList<UserDto> userList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_BY_ID))
        {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    userList.add(this.getUserDto(resultSet));
                }
                if (userList.size() != 1)
                {
                    log.warn("[UserDao] User [" + userId + "] account is not exist!");
                    return null;
                }
                log.info("[UserDao] Account has been found: " + userList.get(0).toString());
                connection.commit();
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
        return userList.iterator().next();
    }

    private UserDto getUserDto(ResultSet resultSet) throws SQLException
    {
        return new UserDto(
                User.builder()
                        .userId(resultSet.getInt("user_id"))
                        .userLogin(resultSet.getString("user_login"))
                        .userPassword(resultSet.getString("user_password"))
                        .userInfo(resultSet.getInt("info_id"))
                        .userRole(resultSet.getInt("role_id")).build(),
                UserInfo.builder()
                        .infoId(resultSet.getInt("info_id"))
                        .userName(resultSet.getString("name"))
                        .userSurname(resultSet.getString("surname"))
                        .userAddress(resultSet.getString("address"))
                        .userBirthday(resultSet.getString("birthday"))
                        .userPhone(resultSet.getString("phone")).build(),
                UserRole.builder()
                        .roleId(resultSet.getInt("role_id"))
                        .roleName(resultSet.getString("role_name"))
                        .roleDesc(resultSet.getString("role_description")).build());
    }
}
