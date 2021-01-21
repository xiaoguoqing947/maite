package com.maite.shuadanmonitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication
@MapperScan("com.maite.shuadanmonitor.shuadantool.mapper")
public class ShuadanMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShuadanMonitorApplication.class, args);
    }
}
