package com.sensesnet.dao.test.dao;

import com.sensesnet.dao.AbstractDao;
import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.pojo.test.TestQuestion;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: Test question.
 * Standart CRUD operation
 */
public class TestQuestionDao extends AbstractDao<TestQuestion>
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
    public TestQuestion getByIdentifier(TestQuestion entity)
    {
        return null;
    }

    @Override
    public List<TestQuestion> getListOfEntity()
    {
        return null;
    }

    @Override
    public void addEntity(TestQuestion entity)
    {

    }

    @Override
    public void removeEntity(TestQuestion entity)
    {

    }

    @Override
    public void editEntity(TestQuestion entity)
    {

    }
}
