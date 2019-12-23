package com.sensesnet.util;

import com.sensesnet.dao.exception.UnknownPropertyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ResourceBundle;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Config class
 */
public class Config
{
    private static Logger log = LogManager.getLogger(Config.class);
    private static String databaseConfigPath = "../test-system-dao/src/main/resources/config/database";
    private static String databaseConfigFileName = "database-config.properties";
    private static String databaseConfigFilePathPattern = databaseConfigPath + "/" + databaseConfigFileName;

    private static String bungleConfigPath = "config.database";
    private static String bungleConfigFileName = "database-config";
    private static String bungleConfigFilePathPattern = bungleConfigPath + "." + bungleConfigFileName;

    public static String getProperty(String constant)
    {
        String value = null;
//        value = getFile(databaseConfigFilePathPattern).exists() ?
//                ResourceBundle.getBundle(bungleConfigFilePathPattern).getString(constant) : null;
        value = ResourceBundle.getBundle(bungleConfigFilePathPattern).getString(constant);
        if (value == null)
            throw new UnknownPropertyException(
                    "[Config] Unable to find property [" + constant + " : " + value + "].");
        log.info("[Config] Property [" + constant + " : " + value + "]");
        return value;
    }

    private static File getFile(String filePath)
    {
        if (!filePath.isEmpty())
            return new File(filePath);
        throw new IllegalArgumentException("[Config] Defined file path is NULL or empty!");
    }
}
