package com.morgon.tradergateway.utils;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zhengyu Wu
 * @date 2019/6/1
 * @description filter
 * @version 1.0.0
 **/
@Component
@WebFilter(urlPatterns = "/*", filterName = "CorsFilter")
public class filter implements Filter {
    public void destroy() {}

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws ServletException, IOException
    {
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {}
}

