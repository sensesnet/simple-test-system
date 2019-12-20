package com.sensesnet.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * TODO: add description
 */
public class HttpRequestValidator
{
    private static final String METHOD_NAME_POST = "POST";

    /**
     * Validate type of request is POST
     *
     * @param reguest
     * @return
     */
    public static boolean isPost(HttpServletRequest reguest) {
        return reguest.getMethod().toUpperCase().equals(METHOD_NAME_POST);
    }
}
