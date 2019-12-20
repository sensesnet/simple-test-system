package com.sensesnet.constant;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Common constant class
 */
public class ConstantProvider
{
    private static PagePath page = new PagePath();
    private static RequestParameter requestParameter = new RequestParameter();
    private static DateFormat dateFormat = new DateFormat();

    public static PagePath getPagePath()
    {
        return page;
    }

    public static RequestParameter getRequestParameter()
    {
        return requestParameter;
    }

    public static DateFormat dateFormat() { return dateFormat; }
}
