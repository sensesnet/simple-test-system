package com.sensesnet.impl;

import com.sensesnet.IUserRoleService;
import com.sensesnet.connection.ConnectionPool;
import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.user.dao.UserRoleDao;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: User role service
 */
public class UserRoleService implements IUserRoleService
{
    private static final Logger log = LogManager.getLogger(UserRoleService.class);
    private UserRoleDao userRoleDao = DaoFactory.getUserRoleDao();

    @Override
    public UserRole getRoleById(UserRole entity) throws ServiceException
    {
        log.info("[UserRoleService] Get users by id: [" + entity.getRoleId() + "].");
        try
        {
            return userRoleDao.getByIdentifier(entity);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Role [" + entity.getRoleId() + " ] has NOT been found.", e);
        }
    }

    @Override
    public List<UserRole> getListOfRoles() throws ServiceException
    {
        log.info("[UserRoleService] Get list with all roles.");
        try
        {
            return userRoleDao.getListOfEntity();
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Role list has not found at test system. Service exception", e);
        }
    }

    @Override
    public void createRole(UserRole entity) throws ServiceException
    {
        log.info("[UserRoleService] Add new role:[" + entity.toString() + "]");
        try
        {
            userRoleDao.addEntity(entity);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "New role [" + entity.toString() + " ] has NOT added to test system.", e);
        }
    }

    @Override
    public void removeRole(UserRole entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Remove role:[" + entity.toString() + "].");
        try
        {
            userRoleDao.removeEntity(entity);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Role [" + entity.toString() + " ] has NOT been removed from test system.", e);
        }
    }

    @Override
    public void editRole(UserRole entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Edit security role:[" + entity.toString() + "].");
        try
        {
            userRoleDao.editEntity(entity);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Role data [" + entity.toString() + " ] has NOT been changed.", e);
        }
    }

    @Override
    public UserRole getRoleById(Integer userRoleId) throws ServiceException
    {
        UserRole userRole = null;
        log.info("[" + this.getClass().getName() + "] Get users by id: [" + userRoleId + "].");
        try
        {
            userRole = userRoleDao.getByIdentifier(userRoleId);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Role [" + userRoleId + " ] has NOT been found.", e);
        }
        return userRole;
    }

    public UserRole getRoleByName(String roleName) throws ServiceException
    {
        UserRole userRole = null;
        log.info("[" + this.getClass().getName() + "] Get users by name: [" + roleName + "].");
        try
        {
            userRole = userRoleDao.getByRoleName(roleName);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Role [" + roleName + " ] has NOT been found.", e);
        }
        return userRole;
    }
}
