package com.sensesnet.dao.test.dao;

import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.TestResult;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: Test result.
 * Standart CRUD operation
 */
public class TestResultDao extends AbstractDao<TestResult>
{
    @Override
    public TestResult getByIdentifier(TestResult entity)
    {
        return null;
    }

    @Override
    public List<TestResult> getListOfEntity()
    {
        return null;
    }

    @Override
    public void addEntity(TestResult entity)
    {

    }

    @Override
    public void removeEntity(TestResult entity)
    {

    }

    @Override
    public void editEntity(TestResult entity)
    {

    }
}
