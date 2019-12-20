package com.sensesnet.implementation;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.user.dao.UserDao;
import com.sensesnet.dto.UserDto;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: User service
 */
public class UserService extends AbstractService<UserDto>
{

    private static final Logger log = LogManager.getLogger(UserService.class);
    private AtomicReference<UserDao> userDao = new AtomicReference<>(DaoFactory.getUserDao());

    /**
     * Authorization service
     *
     * @param login
     * @param password
     * @return
     * @throws SecurityException
     */
    public User authorization(String login, String password) throws SecurityException
    {
        User existingUser = null;
        try
        {
            existingUser = userDao.get().getUserByLoginAndPassword(login, this.getEncryptPassword(password));
            log.info("[Auth Step] User has been found [" + login + ";"
                    + this.getEncryptPassword(password) + "]");
        }
        catch (Exception e)
        {
            log.warn("[Auth step] User is not found by Name and Password:[" + login + ";"
                    + this.getEncryptPassword(password) + "] ");
            throw new SecurityException("Can't find user by Name and Password");
        }
        if (existingUser == null)
        {
            log.error("[Auth step] User account is not found:[" + login + ";"
                    + this.getEncryptPassword(password) + "] ");
            return null;
        }
        log.info("[User Service] Authorization has completed successfully with [" + existingUser.toString() + "].");
        return existingUser;
    }

    @Override
    public UserDto getByIdentifier(UserDto entity) throws ServiceException
    {
        UserDto user = null;
        log.info("[" + this.getClass().getName() + "] Get users by id: [" + entity.getUser().getUserId() + "].");
        try
        {
            user = userDao.get().getByIdentifier(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User [" + entity.getUser().getUserId() + " ] has NOT been found.", e);
        }
        return user;
    }

    @Override
    public List<UserDto> getListOfEntity() throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Get list with all users.");
        CopyOnWriteArrayList<UserDto> userList = null;
        try
        {
            userList = (CopyOnWriteArrayList<UserDto>) userDao.get().getListOfEntity();
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User list has not found at test system. Service exception", e);
        }
        return userList;
    }

    @Override
    public void addEntity(UserDto entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Add new user:[" + entity.toString() + "]");
        try
        {
            entity.getUser()
                    .setUserPassword(getEncryptPassword(entity.getUser().getUserPassword()));
            userDao.get().addEntity(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "New user [" + entity.toString() + " ] has NOT added to test system.", e);
        }
    }

    @Override
    public void removeEntity(UserDto entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Remove user:[" + entity.toString() + "].");
        try
        {
            userDao.get().removeEntity(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User [" + entity.toString() + " ] has NOT been removed from test system.", e);
        }
    }

    @Override
    public void editEntity(UserDto entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Edit user:[" + entity.toString() + "].");
        try
        {
            userDao.get().editEntity(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User data [" + entity.toString() + " ] has NOT been changed.", e);
        }
    }


    /**
     * Password encryption and salt
     *
     * @param password
     * @return
     */
    private String getEncryptPassword(String password)
    {
        String SALT = "SALT";
        return DigestUtils.sha256Hex(password);// + DigestUtils.sha256Hex(SALT);
    }

    /**
     * Get user by login
     *
     * @param login
     * @return
     */
    public User getUserByLogin(String login)
    {
        User existingUser;
        try
        {
            existingUser = userDao.get().getUserByLoginAndPassword(login);
            log.info("[Auth Step] User has been found [" + login + "].");
        }
        catch (Exception e)
        {
            log.warn("[Auth step] User is not found by Login:[" + login + "].");
            throw new SecurityException("Can't find user by Login [" + login + "].");
        }
        if (existingUser == null)
        {
            log.error("[Auth step] User account is not found for: [" + login + "].");
            return null;
        }
        log.info("[User Service] Accoun has been found: [" + existingUser.toString() + "].");
        return existingUser;
    }

    public UserDto getByIdentifier(Integer userId) throws ServiceException
    {
        UserDto user;
        log.info("[" + this.getClass().getName() + "] Get users by id: [" + userId + "].");
        try
        {
            user = userDao.get().getByIdentifier(userId);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User [" + userId.toString() + " ] has NOT been found.", e);
        }
        return user;
    }
}
