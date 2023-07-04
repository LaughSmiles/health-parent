package com.health.backend.config;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(-1)
@WebFilter(filterName = "AuthFilter",urlPatterns = {"/**"})
public class AuthFilter implements Filter {

    //令牌头名字
    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        //检查请求中的cookie
        Cookie[] cookies = httpRequest.getCookies();
        boolean hasAuthorizationCookie = false;
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(AUTHORIZE_TOKEN.equals(cookie.getName())){
                    hasAuthorizationCookie = true;
                    //将cookie添加到请求头中
                    break;
                }
            }
        }

        if (hasAuthorizationCookie) {
            // 存在名为 "Authorization" 的 Cookie，继续执行过滤链
            chain.doFilter(httpRequest, httpResponse);
        } else {
            // 不存在名为 "Authorization" 的 Cookie，重定向到登录页面
            httpResponse.sendRedirect("http://127.0.0.1:9001/pages/login.html");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
