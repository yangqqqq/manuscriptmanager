package com.yang.software.mm.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yang.software.mm.data.session.SessionCache;

public class LoginFilter implements Filter {

    private final static String LOGIN_URL = "/jsp/login/login.jsp";

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        String sessionId = servletRequest.getSession().getId();

        System.out.println(servletRequest.getRequestURL());
        SessionCache.sessionId.set(sessionId);

        if (servletRequest.getServletPath().equals(LoginFilter.LOGIN_URL)
                || servletRequest.getServletPath().equals("/login")
                || servletRequest.getServletPath().startsWith("/image/")
                || servletRequest.getServletPath().startsWith("/js/")
                || servletRequest.getServletPath().startsWith("/manuscriptmanager/css/")) {
            chain.doFilter(request, response);
            return;
        }
        if (!SessionCache.isSessionExist(sessionId)) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig arg0) throws ServletException {

    }

}
