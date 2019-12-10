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
     * - get impl
     *
     * @param entity
     * @return
     */
    public abstract T getByIdentifier(T entity) throws ServiceException;

    /**
     * Service
     * - get List<T> of impl
     *
     * @return
     */
    public abstract List<T> getListOfEntity() throws ServiceException;

    /**
     * Service
     * - set impl
     *
     * @param entity
     */
    public abstract void addEntity(T entity) throws ServiceException;

    /**
     * Service
     * - remove Entity
     *
     * @param entity
     */
    public abstract void removeEntity(T entity) throws ServiceException;

    /**
     * Service
     * - edit impl
     */
    public abstract void editEntity(T entity) throws ServiceException;

}
