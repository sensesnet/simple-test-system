package com.sensesnet.dao.test.dao;

import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.TestAnswer;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: Test answer
 * Standart CRUD operation
 */
public class TestAnswerDao extends AbstractDao<TestAnswer>
{
    @Override
    public TestAnswer getByIdentifier(TestAnswer entity)
    {
        return null;
    }

    @Override
    public List<TestAnswer> getListOfEntity()
    {
        return null;
    }

    @Override
    public void addEntity(TestAnswer entity)
    {

    }

    @Override
    public void removeEntity(TestAnswer entity)
    {

    }

    @Override
    public void editEntity(TestAnswer entity)
    {

    }

}
