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

@WebFilter("/")
public class EncodingRequestFilter implements Filter
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
        request.setCharacterEncoding("UTF-8");
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
