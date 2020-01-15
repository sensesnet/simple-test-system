package com.sensesnet.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author sensesnet <br />
 * Copyright 2020 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class EncryptPassword
{
    public static String getEncryptPassword(String password)
    {
        String SALT = "SALT";
        return DigestUtils.sha256Hex(password);// + DigestUtils.sha256Hex(SALT);
    }
}
