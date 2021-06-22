package com.youruan.dentistry.console;

import com.youruan.dentistry.console.base.interceptor.AuthenticationInterceptor;
import com.youruan.dentistry.console.base.interceptor.SessionInterceptor;
import com.youruan.dentistry.console.base.resolver.HandlerSessionArgumentResolver;
import com.youruan.dentistry.console.base.utils.JwtTokenUtils;
import com.youruan.dentistry.core.platform.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final EmployeeService employeeService;
    private final JwtTokenUtils jwtTokenUtils;

    public WebMvcConfig(EmployeeService employeeService, JwtTokenUtils jwtTokenUtils) {
        this.employeeService = employeeService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Bean
    public SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor(employeeService);
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor(jwtTokenUtils,employeeService);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/platform/employee/*");
        registry.addInterceptor(sessionInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new HandlerSessionArgumentResolver(employeeService));
    }
}
