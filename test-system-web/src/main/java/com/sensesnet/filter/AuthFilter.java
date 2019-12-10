package com.sensesnet.filter;

import com.sensesnet.implementation.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * WebFilter: Authentification filter
 */

//@WebFilter
//public class AuthFilter implements Filter
//{
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException
//    {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest,
//                         ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException
//    {
//
//        final HttpServletRequest req = (HttpServletRequest) servletRequest;
//        final HttpServletResponse res = (HttpServletResponse) servletResponse;
//        final String login = req.getParameter("login");
//        final String password = req.getParameter("password");
//
//    }
//
//    @Override
//    public void destroy()
//    {
//
//    }
//}
