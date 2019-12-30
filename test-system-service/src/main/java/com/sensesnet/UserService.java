package com.sensesnet;

import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.User;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Interface provides methods for User entity.
 */
public interface UserService
{
    /**
     * get user by email
     *
     * @param email
     * @throws ServiceException
     * @return
     */
    User getUserByEmail(String email) throws ServiceException;

    /**
     * get user by login and password
     *
     * @param email
     * @param password
     * @throws ServiceException
     * @return
     */
    User getUserByEmailAndPassword(String email, String password) throws ServiceException;

    /**
     * create user
     *
     * @param entity
     * @throws ServiceException
     */
    void createUser(User entity) throws ServiceException;
    /**
     * get user by id
     *
     * @param entity
     * @throws ServiceException
     * @return
     */
    User getUserById(User entity) throws ServiceException;

    /**
     * get user by id
     *
     * @param userId
     * @throws ServiceException
     * @return
     */
    User getUserById(Integer userId) throws ServiceException;

    /**
     * update user
     *
     * @param user
     * @throws ServiceException
     */
    void updateUser(User user) throws ServiceException;

    /**
     * remove user
     *
     * @param entity
     * @throws ServiceException
     */
    void removeUser(User entity) throws ServiceException;

    /**
     * get list of users
     *
     * @return
     * @throws ServiceException
     */
    List<User> getListOfEntity() throws ServiceException;

    /**
     * change user password
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    User changeUserPassword(int userId, String oldPassword, String newPassword);


    /**
     * Validate user data
     * sign up
     *
     * @param email
     * @param password
     * @return
     */
    String checkUserData(String email, String password);

    /**
     * Validate user email
     *
     * @param email
     *            user email
     * @return appropriate message as a result of check
     */
    String checkUserEmail(String email);

    /**
     * Validate user password
     *
     * @param password
     * @return
     */
    String checkUserPassword(String password, String confirmPassword);

//    /**
//     * buildUser
//     */
//    User buildUser(HttpServletRequest request);
//
//    /**
//     * checks is user role admin
//     *
//     * @param request
//     *            HttpServletRequest object
//     * @return {@code true} if user is admin, {@code false} otherwise
//     */
//    boolean isUserAdmin(HttpServletRequest request);
}
