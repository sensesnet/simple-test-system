package com.sensesnet.dao.user.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.Constant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.authentication.UserInfo;
import com.sensesnet.pojo.authentication.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: user role
 * Standart CRUD operation
 */
public class UserRoleDao extends AbstractDao<UserRole>
{
    private static final Logger log = LogManager.getLogger(UserRoleDao.class);

    @Override
    public UserRole getByIdentifier(UserRole entity) throws ConnectionPoolException, DaoException
    {
        LinkedList<UserRole> userRoleList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().SELECT_USER_ROLE_BY_ID))
        {
            statement.setInt(1, entity.getRoleId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    userRoleList.add(UserRole.builder()
                            .roleId(resultSet.getInt("role_id"))
                            .roleName(resultSet.getString("role_name"))
                            .roleDesc(resultSet.getString("role_description")).build());
                }
                assert userRoleList.size() == 1;
                log.info("[UserDao] Role has been selected by id: " + entity.getRoleId());
            }
        }
        catch (SQLException e)
        {
            throw new DaoException("SQL Error: Have no access to DB.", e);
        }
        catch (AssertionError e)
        {
            log.info("[Auth] Role [" + entity.getRoleId() + "] account is not exist!");
            return null;
        }
        finally
        {
            closeConnection(connection);
        }
        return userRoleList.iterator().next();
    }

    @Override
    public List<UserRole> getListOfEntity() throws DaoException, ConnectionPoolException
    {
        List<UserRole> roleList = new ArrayList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().SELECT_ALL_USER_ROLE))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    roleList.add(UserRole.builder()
                            .roleId(resultSet.getInt("role_id"))
                            .roleName(resultSet.getString("role_name"))
                            .roleDesc(resultSet.getString("role_description")).build());
                }
                log.info("[UserDao] All account's roles has been selected.");
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
        return roleList;
    }

    @Override
    public void addEntity(UserRole entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().INSERT_NEW_USER_ROLE))
        {
            prepareStatementParams(
                    statement,
                    entity.getRoleName(),
                    entity.getRoleDesc()).executeUpdate();
            log.info("[UserDao] New account role has been added: " + entity.toString());
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
    public void removeEntity(UserRole entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().DELETE_USER_ROLE_BY_ID))
        {
            statement.setInt(1, entity.getRoleId());
            statement.execute();
            log.info("[UserDao] Account role has been removed: " + entity.toString());
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
    public void editEntity(UserRole entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(Constant.query().UPDATE_USER_ROLE))
        {
            prepareStatementParams(
                    statement,
                    entity.getRoleName(),
                    entity.getRoleDesc(),
                    entity.getRoleId()).executeQuery();
            log.info("[UserDao] Account role Info has been updated: " + entity.toString());
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
