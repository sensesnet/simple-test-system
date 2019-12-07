package com.sensesnet.implementation;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.user.dao.UserDao;
import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: User service
 */
public class UserService extends AbstractService<User>
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
    public User getByIdentifier(User entity) throws ServiceException
    {
        User user = null;
        log.info("[" + this.getClass().getName() + "] Get users by id: [" + entity.getUserId() + "].");
        try
        {
            user = userDao.get().getByIdentifier(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User [" + entity.getUserId() + " ] has NOT been found.", e);
        }
        return user;
    }

    @Override
    public List<User> getListOfEntity() throws ConnectionPoolException, DaoException
    {
        log.info("[" + this.getClass().getName() + "] Get list with all users.");
        return userDao.get().getListOfEntity();
    }

    @Override
    public void addEntity(User entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Add new user:[" + entity.toString() + "]");
        try
        {
            userDao.get().addEntity(entity);
        }
        catch (Exception e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "New user [" + entity.toString() + " ] has NOT added to test system.", e);
        }
    }

    @Override
    public void removeEntity(User entity) throws ServiceException
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
    public void editEntity(User entity) throws ServiceException
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
}
