package com.sensesnet.impl;

import com.sensesnet.IUserService;
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


/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: User service
 */
public class UserService implements IUserService
{
    private static final Logger log = LogManager.getLogger(UserService.class);
    private UserDao userDao = DaoFactory.getUserDao();

    /**
     * Authorization service
     *
     * @param login
     * @param password
     * @return
     * @throws SecurityException
     */
    @Override
    public User getUserByEmailAndPassword(String login, String password) throws ServiceException
    {
        User existingUser = null;
        try
        {
            existingUser = userDao.getUserByLoginAndPassword(login, this.getEncryptPassword(password));
            if (existingUser == null)
            {
                log.error("[Auth step] User account is not found:[" + login + ";"
                        + this.getEncryptPassword(password) + "] ");
                return null;
            }
            log.info("[User Service] Authorization has completed successfully with [" + existingUser.toString() + "].");
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new SecurityException("[Auth step] User is not found by Name and Password:[" + login + ";" +
                    "" + this.getEncryptPassword(password) + "]");
        }
        return existingUser;
    }

    @Override
    public User getUserById(User entity) throws ServiceException
    {
        log.info("[" + this.getClass().getName() + "] Get users by id: [" + entity.getUserId() + "].");
        try
        {
            return userDao.getByIdentifier(entity);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User [" + entity.getUserId() + " ] has NOT been found.", e);
        }
    }

    @Override
    public List<User> getListOfEntity() throws ServiceException
    {

        log.info("[UserService] Get list with all users.");
        try
        {
            return userDao.getListOfEntity();
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User list has not found at test system. Service exception", e);
        }
    }

    @Override
    public void createUser(User entity) throws ServiceException
    {
        log.info("[UserService] Add new user:[" + entity.toString() + "]");
        try
        {
            entity.setUserPassword(getEncryptPassword(entity.getUserPassword()));
            userDao.addEntity(entity);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "New user [" + entity.toString() + " ] has NOT added to test system.", e);
        }
    }

    @Override
    public void removeUser(User entity) throws ServiceException
    {
        log.info("[UserService] Remove user:[" + entity.toString() + "].");
        try
        {
            userDao.removeEntity(entity);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User [" + entity.toString() + " ] has NOT been removed from test system.", e);
        }
    }

    @Override
    public void updateUser(User entity) throws ServiceException
    {
        log.info("[UserService] Edit user:[" + entity.toString() + "].");
        try
        {
            userDao.editEntity(entity);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[" + this.getClass().getName() + "] "
                    + "User data [" + entity.toString() + " ] has NOT been changed.", e);
        }
    }

    /**
     * get user by email
     *
     * @param email
     * @return
     */
    @Override
    public User getUserByEmail(String email) throws ServiceException
    {
        log.info("[UserService] Get users by email (login) [" + email + "].");
        try
        {
            return userDao.getUserByLogin(email);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("Can't find user by Login [" + email + "].");
        }
    }

    /**
     * get user entity by id
     *
     * @param userId
     * @return
     * @throws ServiceException
     */
    @Override
    public User getUserById(Integer userId) throws ServiceException
    {
        log.info("[UserService] Get users by id: [" + userId + "].");
        try
        {
            return userDao.getByIdentifier(userId);
        }
        catch (ConnectionPoolException | DaoException e)
        {
            throw new ServiceException("[UserService] User [" + userId.toString() + " ] has NOT been found.", e);
        }
    }


    @Override
    public User changeUserPassword(int userId, String oldPassword, String newPassword)
    {
        return null;
    }

    @Override
    public String checkUserData(String email, String password)
    {
        return null;
    }

    @Override
    public String checkUserEmail(String email)
    {
        return null;
    }

    @Override
    public String checkUserPassword(String password, String confirmPassword)
    {
        return null;
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
