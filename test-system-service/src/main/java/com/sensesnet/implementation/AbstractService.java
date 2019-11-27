package com.sensesnet.implementation;

import com.sensesnet.dao.exception.DaoException;
import com.sensesnet.exception.ServiceException;

import java.util.List;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Service: Abstract service
 */
public abstract class AbstractService<T>
{
    /**
     * Service
     * - get entity
     *
     * @param entity
     * @return
     */
    public abstract T getByIdentifier(T entity) throws DaoException, ServiceException;

    /**
     * Service
     * - get List<T> of entities
     *
     * @return
     */
    public abstract List<T> getListOfEntity() throws DaoException, ServiceException;

    /**
     * Service
     * - set entity
     *
     * @param entity
     */
    public abstract void addEntity(T entity) throws DaoException, ServiceException;

    /**
     * Service
     * - remove Entity
     *
     * @param entity
     */
    public abstract void removeEntity(T entity) throws DaoException, ServiceException;

    /**
     * Service
     * - edit entity
     */
    public abstract void editEntity(T entity) throws DaoException, ServiceException;

}
