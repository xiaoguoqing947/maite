package com.maite.shuadanmonitor.shuadantool.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：xiao guo qing
 * @date ：2021/2/3 17:25
 * @description：
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //获取文件的真实路径
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\goods\\";
        registry.addResourceHandler("/images/goods/**").addResourceLocations("file:"+path);
    }
}
