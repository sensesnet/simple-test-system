package com.sensesnet.implementation;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.user.dao.UserDao;
import com.sensesnet.dao.user.dao.UserRoleDao;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
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
public class UserRoleService extends AbstractService<UserRole>
{
    private static final Logger log = LogManager.getLogger(UserRoleService.class);
    private UserRoleDao userRoleDao = DaoFactory.getUserRoleDao();

    @Override
    public UserRole getByIdentifier(UserRole entity) throws ServiceException
    {
        UserRole userRole = null;
        log.info("[" + this.getClass().getName() + "] Get users by id: [" + entity.getRoleId() + "].");
        try
        {
            userRole = userRoleDao.getByIdentifier(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Role [" + entity.getRoleId() + " ] has NOT been found.", e);
        }
        return userRole;
    }

    @Override
    public List<UserRole> getListOfEntity() throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Get list with all roles.");
        CopyOnWriteArrayList<UserRole> userRoleList = null;
        try
        {
            userRoleList = (CopyOnWriteArrayList<UserRole>) userRoleDao.getListOfEntity();
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Role list has not found at test system. Service exception", e);
        }
        return userRoleList;
    }

    @Override
    public void addEntity(UserRole entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Add new role:[" + entity.toString() + "]");
        try
        {
            userRoleDao.addEntity(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "New role [" + entity.toString() + " ] has NOT added to test system.", e);
        }
    }

    @Override
    public void removeEntity(UserRole entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Remove role:[" + entity.toString() + "].");
        try
        {
            userRoleDao.removeEntity(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Role [" + entity.toString() + " ] has NOT been removed from test system.", e);
        }
    }

    @Override
    public void editEntity(UserRole entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Edit security role:[" + entity.toString() + "].");
        try
        {
            userRoleDao.editEntity(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "Role data [" + entity.toString() + " ] has NOT been changed.", e);
        }
    }

    public UserRole getByIdentifier(Integer userRoleId) throws ServiceException
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
}
