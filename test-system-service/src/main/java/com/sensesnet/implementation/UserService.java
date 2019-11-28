package com.sensesnet.implementation;

import com.sensesnet.connection.ConnectionPoolException;
import com.sensesnet.dao.DaoFactory;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.dao.user.dao.UserDao;
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
public class UserService extends AbstractService<User>
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
    public User authorization(String login, String password) throws SecurityException
    {
        User existingUser = null;
        try
        {
            existingUser = userDao.getUserByLoginAndPassword(login, this.getEncryptPassword(password));
            assert existingUser != null;
            log.info("[Auth Step] User has been found [" + login + ";"
                    + this.getEncryptPassword(password) + "]");
        }
        catch (DaoException e)
        {
            log.error("[Auth step] User is not found by Name and Password:[" + login + ";"
                    + this.getEncryptPassword(password) + "] ");
            throw new SecurityException("Can't find user by Name and Password");
        }
        catch (AssertionError e){
            log.error("[Auth step] User account is not found:[" + login + ";"
                    + this.getEncryptPassword(password) + "] ");
            throw new SecurityException("User account is not found!");
        }
        catch (ConnectionPoolException e)
        {
            e.printStackTrace();
        }
        return existingUser;
    }

    @Override
    public User getByIdentifier(User entity)
    {
        return null;
    }

    @Override
    public List<User> getListOfEntity()
    {
        return null;
    }

    @Override
    public void addEntity(User entity)
    {

    }

    @Override
    public void removeEntity(User entity)
    {

    }

    @Override
    public void editEntity(User entity)
    {

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
        return DigestUtils.sha256Hex(password) + DigestUtils.sha256Hex(SALT);
    }
}
