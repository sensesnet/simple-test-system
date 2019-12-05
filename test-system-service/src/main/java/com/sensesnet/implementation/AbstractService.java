package com.sensesnet.implementation;

import com.sensesnet.connection.ConnectionPoolException;
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
     * - get command
     *
     * @param entity
     * @return
     */
    public abstract T getByIdentifier(T entity) throws DaoException, ServiceException;

    /**
     * Service
     * - get List<T> of command
     *
     * @return
     */
    public abstract List<T> getListOfEntity() throws DaoException, ServiceException, ConnectionPoolException;

    /**
     * Service
     * - set command
     *
     * @param entity
     */
    public abstract void addEntity(T entity) throws DaoException, ServiceException, ConnectionPoolException;

    /**
     * Service
     * - remove Entity
     *
     * @param entity
     */
    public abstract void removeEntity(T entity) throws DaoException, ServiceException;

    /**
     * Service
     * - edit command
     */
    public abstract void editEntity(T entity) throws DaoException, ServiceException;

}
