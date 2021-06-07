package com.youruan.dentistry.console;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileStaticConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         *  /bannerImg/xxx   指文件的访问方式  如：localhost:8080/bannerImg/abc.jpg
         *  file:c/image/  指静态文件存放在服务器上的位置
         */
        registry.addResourceHandler("/bannerImg/**").addResourceLocations("file:"+"c:/image/");
    }
}
