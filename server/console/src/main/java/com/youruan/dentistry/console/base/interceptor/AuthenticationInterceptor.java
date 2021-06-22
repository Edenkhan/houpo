package com.youruan.dentistry.console.base.interceptor;

import com.youruan.dentistry.console.base.utils.JwtTokenUtils;
import com.youruan.dentistry.console.base.utils.SessionUtils;
import com.youruan.dentistry.core.platform.domain.Employee;
import com.youruan.dentistry.core.platform.service.EmployeeService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {

    private final JwtTokenUtils jwtTokenUtils;
    private final EmployeeService employeeService;

    public AuthenticationInterceptor(JwtTokenUtils jwtTokenUtils, EmployeeService employeeService) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.employeeService = employeeService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //进入方法之前进行的操作
        //获取token
        String token  =  request.getHeader("token");
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        boolean hasPassToken = method.hasMethodAnnotation(PassToken.class);
        boolean hasUserLoginToken = method.hasMethodAnnotation(UserLoginToken.class);
        if(hasPassToken) return true;

        if(hasUserLoginToken) {
            //获取token的username
            String username = jwtTokenUtils.getUserNameFromToken(token);
            // 查询数据库
            Employee employee = employeeService.getByUsername(username);
            // 将用户保存到Session
            if (employee!=null) SessionUtils.login(username);
            return true;
        }
        return true;
    }
}
