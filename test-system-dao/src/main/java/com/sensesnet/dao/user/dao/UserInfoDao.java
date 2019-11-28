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
 * DAO: user info
 * Standart CRUD operation
 */
public class UserInfoDao extends AbstractDao<UserInfoDao>
{
    private static final Logger log = LogManager.getLogger(UserInfoDao.class);

    @Override
    public UserInfoDao getByIdentifier(UserInfoDao entity)
    {
        return null;
    }

    @Override
    public List<UserInfoDao> getListOfEntity()
    {
        return null;
    }

    @Override
    public void addEntity(UserInfoDao entity)
    {

    }

    @Override
    public void removeEntity(UserInfoDao entity)
    {

    }

    @Override
    public void editEntity(UserInfoDao entity)
    {

    }

}
