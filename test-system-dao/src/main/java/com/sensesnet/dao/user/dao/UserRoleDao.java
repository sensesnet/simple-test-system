package com.sensesnet.dao.user.dao;

import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: user role
 * Standart CRUD operation
 */
public class UserRoleDao extends AbstractDao<UserRoleDao>
{
    private static final Logger log = LogManager.getLogger(UserRoleDao.class);

    @Override
    public UserRoleDao getByIdentifier(UserRoleDao entity)
    {
        return null;
    }

    @Override
    public List<UserRoleDao> getListOfEntity()
    {
        return null;
    }

    @Override
    public void addEntity(UserRoleDao entity)
    {

    }

    @Override
    public void removeEntity(UserRoleDao entity)
    {

    }

    @Override
    public void editEntity(UserRoleDao entity)
    {

    }
}
