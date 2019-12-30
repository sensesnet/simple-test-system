package com.sensesnet.dao.user.dao;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.constant.DaoConstant;
import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.authentication.User;
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
import java.util.concurrent.CopyOnWriteArrayList;

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

    public UserRole getByIdentifier(Integer userRoleId) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_ROLE_BY_ID))
        {
            statement.setInt(1, userRoleId);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[" + this.getClass().getName() + "] Role has been selected by id: " + userRoleId);
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

    /**
     * get role by name
     *
     * @param roleName
     * @return
     * @throws ConnectionPoolException
     * @throws DaoException
     */
    public UserRole getByRoleName(String roleName) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_ROLE_BY_NAME))
        {
            statement.setString(1, roleName);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[" + this.getClass().getName() + "] Role has been selected by name: " + roleName);
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

    /**
     * get role by id
     *
     * @param entity
     * @return
     * @throws ConnectionPoolException
     * @throws DaoException
     */
    @Override
    public UserRole getByIdentifier(UserRole entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_USER_ROLE_BY_ID))
        {
            statement.setInt(1, entity.getRoleId());
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return this.buildEntity(resultSet);
                }
                log.info("[" + this.getClass().getName() + "] Role has been selected by id: " + entity.getRoleId());
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

    /**
     * list of roles
     *
     * @return
     * @throws DaoException
     * @throws ConnectionPoolException
     */
    @Override
    public List<UserRole> getListOfEntity() throws DaoException, ConnectionPoolException
    {
        LinkedList<UserRole> roleList = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement statement = connection
                .prepareStatement(DaoConstant.query().SELECT_ALL_USER_ROLE))
        {
            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    roleList.add(this.buildEntity(resultSet));
                }
                log.info("[" + this.getClass().getName() + "] All account's roles has been selected.");
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
        return roleList;
    }

    /**
     * add new role
     *
     * @param entity
     * @throws ConnectionPoolException
     * @throws DaoException
     */
    @Override
    public void addEntity(UserRole entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().INSERT_NEW_USER_ROLE))
            {
                prepareStatementParams(
                        statement,
                        entity.getRoleName(),
                        entity.getRoleDesc()).executeUpdate();
                log.info("[" + this.getClass().getName() + "] Role has been added: " + entity.toString());
                connection.commit();
            }
        }
        catch (SQLException e)
        {
            log.error("[" + this.getClass().getName() + "] Role has NOT added, DB access error.", e);
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

    /**
     * remove role entity
     *
     * @param entity
     * @throws ConnectionPoolException
     * @throws DaoException
     */
    @Override
    public void removeEntity(UserRole entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().DELETE_USER_ROLE_BY_ID))
            {
                statement.setInt(1, entity.getRoleId());
                statement.execute();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] Account role has been removed: " + entity.toString());
            }
        }
        catch (SQLException e)
        {
            log.error("[" + this.getClass().getName() + "] Role has NOT removed, DB access error.", e);
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
    public void editEntity(UserRole entity) throws ConnectionPoolException, DaoException
    {
        Connection connection = getConnection();
        try
        {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection
                    .prepareStatement(DaoConstant.query().UPDATE_USER_ROLE))
            {
                prepareStatementParams(
                        statement,
                        entity.getRoleName(),
                        entity.getRoleDesc(),
                        entity.getRoleId()).executeQuery();
                connection.commit();
                log.info("[" + this.getClass().getName() + "] Account role has been updated: " + entity.toString());
            }
        }
        catch (SQLException e)
        {
            log.error("[" + this.getClass().getName() + "] Role has NOT updated, DB access error.", e);
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

    /**
     * user role builder
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    @Override
    public UserRole buildEntity(ResultSet resultSet) throws SQLException
    {
        return UserRole.builder()
                .roleId(resultSet.getInt("role_id"))
                .roleName(resultSet.getString("role_name"))
                .roleDesc(resultSet.getString("role_description")).build();
    }
}
