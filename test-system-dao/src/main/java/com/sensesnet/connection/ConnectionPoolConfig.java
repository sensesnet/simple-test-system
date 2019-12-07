package com.sensesnet.connection;

import com.sensesnet.constant.DAOConstant;
import com.sensesnet.util.Config;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Connection Pool: Config
 */
public interface ConnectionPoolConfig
{
    String databaseServerName = Config.getProperty(DAOConstant.dbConnection().DATABASE_SERVER_NAME);
    String databasePortNumber = Config.getProperty(DAOConstant.dbConnection().DATABASE_PORT_NUMBER);
    String databaseName = Config.getProperty(DAOConstant.dbConnection().DATABASE_NAME);
    String username = Config.getProperty(DAOConstant.dbConnection().DATABASE_USERNAME);
    String password = Config.getProperty(DAOConstant.dbConnection().DATABASE_PASSWORD);
    String databaseClass = Config.getProperty(DAOConstant.dbConnection().DATABASE_DB_CLASS);
    String databaseScheme = Config.getProperty(DAOConstant.dbConnection().DATABASE_SCHEME);
    String databaseLink =
            "jdbc:mysql://"
                    + databaseServerName + ":"
                    + databasePortNumber + "/" + databaseScheme
                    + "?useUnicode=true&serverTimezone=UTC&useSSL=false";
    Integer connectionCount = Integer.valueOf(Config.getProperty(DAOConstant.dbConnection().CONNECTION_COUNT));
}
