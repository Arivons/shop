package com.frontend.cart.interceptor;

import com.frontend.cart.service.UserCheckService;
import com.pojo.TbUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserCheckService userCheckService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if(StringUtils.isBlank(token)){
            return false;
        }
        TbUser tbUser = userCheckService.checkUser(token);
        if(tbUser==null){
            return false;
        }
        return true;
    }
}
