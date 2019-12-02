package com.sensesnet.filter;

import com.sensesnet.exception.ServiceException;
import com.sensesnet.implementation.UserService;
import com.sensesnet.pojo.authentication.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * Sign in filter
 */

@WebFilter("/ServletFilter")
public class ServletFilter implements Filter
{
    private FilterConfig filterConfig;
    private static ArrayList<String> pages;

    public ServletFilter()
    {
        if (pages == null)
            pages = new ArrayList<String>();
    }

    /**
     * Filter Initialization
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException
    {
        this.filterConfig = config;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {

    }

    /**
     * destroy filter
     */
    @Override
    public void destroy()
    {
        filterConfig = null;
    }

//   ih
}
