package com.sensesnet.dao.test.dao;

import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.Test;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: Test pojo
 * Standart CRUD operation
 */
public class TestDao extends AbstractDao<Test>
{
    @Override
    public String getCreateQuery() throws DaoException
    {
        return null;
    }

    @Override
    public String getSelectQuery() throws DaoException
    {
        return null;
    }

    @Override
    public String getUpdateQuery() throws DaoException
    {
        return null;
    }

    @Override
    public String getDeleteQuery() throws DaoException
    {
        return null;
    }

    @Override
    public Test getByIdentifier(Test entity)
    {
        return null;
    }

    @Override
    public List<Test> getListOfEntity()
    {
        return null;
    }

    @Override
    public void addEntity(Test entity)
    {

    }

    @Override
    public void removeEntity(Test entity)
    {

    }

    @Override
    public void editEntity(Test entity)
    {

    }

}
