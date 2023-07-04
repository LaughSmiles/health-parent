package com.health.backend.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class FeignInterceptor implements RequestInterceptor {

    //令牌头名字
    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            //使用RequestContextHolder工具获取request相关变量
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                //取出request
                HttpServletRequest request = attributes.getRequest();
                //取出cookie对象
                Cookie[] cookies = request.getCookies();
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if (AUTHORIZE_TOKEN.equals((cookie.getName()))){
                        //将cookie添加到请求头中
                        requestTemplate.header(AUTHORIZE_TOKEN,  "Bearer " + cookie.getValue());
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}