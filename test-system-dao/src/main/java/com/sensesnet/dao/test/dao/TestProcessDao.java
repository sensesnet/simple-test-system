package com.sensesnet.dao.test.dao;

import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.TestProcess;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: Test process
 * Standart CRUD operation
 */
public class TestProcessDao extends AbstractDao<TestProcess>
{
    @Override
    public TestProcess getByIdentifier(TestProcess entity)
    {
        return null;
    }

    @Override
    public List<TestProcess> getListOfEntity()
    {
        return null;
    }

    @Override
    public void addEntity(TestProcess entity)
    {

    }

    @Override
    public void removeEntity(TestProcess entity)
    {

    }

    @Override
    public void editEntity(TestProcess entity)
    {

    }
}
