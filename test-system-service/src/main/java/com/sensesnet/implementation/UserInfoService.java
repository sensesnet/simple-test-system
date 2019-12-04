package com.sensesnet.implementation;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.user.dao.UserInfoDao;
import com.sensesnet.dao.user.dao.UserRoleDao;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.UserInfo;
import com.sensesnet.pojo.authentication.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: User info service
 */
public class UserInfoService extends AbstractService<UserInfo>
{
    private static final Logger log = LogManager.getLogger(UserInfoService.class);
    private UserInfoDao userInfoDao = DaoFactory.getUserInfoDao();

    @Override
    public UserInfo getByIdentifier(UserInfo entity) throws ServiceException
    {
        UserInfo userInfo = null;
        log.info("[" + this.getClass().getName() + "] Get users info by id: [" + entity.getInfoId() + "].");
        try
        {
            userInfo = userInfoDao.getByIdentifier(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User info [" + entity.getInfoId() + " ] has NOT been found.", e);
        }
        return userInfo;
    }

    @Override
    public List<UserInfo> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        log.info("[" + this.getClass().getName() + "] Get list with all roles.");
        return userInfoDao.getListOfEntity();
    }

    @Override
    public void addEntity(UserInfo entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Add new user info:[" + entity.toString() + "]");
        try
        {
            userInfoDao.addEntity(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "New user info [" + entity.toString() + " ] has NOT added to test system.", e);
        }
    }

    @Override
    public void removeEntity(UserInfo entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Remove user info:[" + entity.toString() + "].");
        try
        {
            userInfoDao.removeEntity(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User info [" + entity.toString() + " ] has NOT been removed from test system.", e);
        }
    }

    @Override
    public void editEntity(UserInfo entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Edit user info:[" + entity.toString() + "].");
        try
        {
            userInfoDao.editEntity(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User info data [" + entity.toString() + " ] has NOT been changed.", e);
        }
    }
}
