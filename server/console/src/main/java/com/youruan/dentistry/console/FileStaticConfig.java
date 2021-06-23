package com.youruan.dentistry.console;

import com.youruan.dentistry.core.base.storage.DiskFileStorageProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileStaticConfig implements WebMvcConfigurer {

    private final DiskFileStorageProperties diskFileStorageProperties;

    public FileStaticConfig(DiskFileStorageProperties diskFileStorageProperties) {
        this.diskFileStorageProperties = diskFileStorageProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         *  /img/xxx   指文件的访问方式  如：localhost:8080/img/abc.jpg
         *  file:c/image/  指静态文件存放在服务器上的位置
         */
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:"+diskFileStorageProperties.getBaseDirectory()+"/");
    }
}
