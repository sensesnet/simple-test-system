package com.sensesnet.constant;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DAO: Common constant provider
 */
public class DaoConstant
{
    private final static SQLQueryConstant sqlQueryConstant = new SQLQueryConstant();
    private final static DbConnectionConstant dbConnectionConstant = new DbConnectionConstant();

    /**
     * SQL query
     *
     * @return
     */
    public static SQLQueryConstant query()
    {
        return sqlQueryConstant;
    }

    /**
     * Db constants
     *
     * @return
     */
    public static DbConnectionConstant dbConnection()
    {
        return dbConnectionConstant;
    }
}
