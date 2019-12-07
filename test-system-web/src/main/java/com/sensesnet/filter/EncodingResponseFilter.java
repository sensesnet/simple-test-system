package com.sensesnet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Sign in filter
 */

@WebFilter("/")
public class EncodingResponseFilter implements Filter
{
    /**
     * Filter Initialization
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response);
    }

    /**
     * Destroy filter
     */
    @Override
    public void destroy()
    {

    }
}
