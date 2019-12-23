package com.sensesnet;

import com.sensesnet.exception.ServiceException;
import com.sensesnet.pojo.authentication.UserRole;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Interface provides methods for user role entity
 */
public interface IUserRoleService
{
    /**
     * get user role by id
     *
     * @param entity
     * @return
     * @throws ServiceException
     */
    UserRole getRoleById(UserRole entity) throws ServiceException;

    /**
     * get list of roles
     *
     * @return
     * @throws ServiceException
     */
    List<UserRole> getListOfRoles() throws ServiceException;

    /**
     * create new role
     *
     * @param entity
     * @throws ServiceException
     */
    void createRole(UserRole entity) throws ServiceException;

    /**
     * remove role
     *
     * @param entity
     * @throws ServiceException
     */
    void removeRole(UserRole entity) throws ServiceException;

    /**
     * edit role
     *
     * @param entity
     * @throws ServiceException
     */
    void editRole(UserRole entity) throws ServiceException;

    /**
     * get role by id
     *
     * @param userRoleId
     * @return
     * @throws ServiceException
     */
    UserRole getRoleById(Integer userRoleId) throws ServiceException;

    /**
     * get role by name
     *
     * @param roleName
     * @return
     * @throws ServiceException
     */
    UserRole getRoleByName(String roleName) throws ServiceException;

}
