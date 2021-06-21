package com.youruan.dentistry.console.base.interceptor;

import com.youruan.dentistry.console.base.utils.JwtTokenUtils;
import com.youruan.dentistry.core.platform.domain.Employee;
import com.youruan.dentistry.core.platform.service.EmployeeService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

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
        if(!(handler instanceof HandlerMethod))
        {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(PassToken.class))
        {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }
        String username = null;
        if(method.isAnnotationPresent(UserLoginToken.class))
        {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if(userLoginToken.required()){
                if(token == null){
                    throw new RuntimeException("无token，请重新登录");
                }
                //获取token的username
                username = jwtTokenUtils.getUserNameFromToken(token);
                if(username == null) {
                    throw new RuntimeException("无username，请重新登录");
                }
                Employee employee = employeeService.getByUsername(username);
                if(employee==null){
                    throw new RuntimeException("用户不存在");
                }
                //验证token
                boolean flag = jwtTokenUtils.validateToken(token, employee);
                if(!flag) {
                    throw new RuntimeException("401，验证未通过");
                }

                return true;
            }
        }
        return true;
    }
}
