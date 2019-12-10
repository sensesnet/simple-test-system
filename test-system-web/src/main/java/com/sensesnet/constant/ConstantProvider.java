package com.sensesnet.constant;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Common constant class
 */
public class ConstantProvider
{
    private static Page page = new Page();
    private static RequestParameter requestParameter = new RequestParameter();

    public static Page getPagePath()
    {
        return page;
    }

    public static RequestParameter getRequestParameter()
    {
        return requestParameter;
    }
}
