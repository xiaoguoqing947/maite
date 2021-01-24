package com.maite.shuadanmonitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Administrator
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.maite.shuadanmonitor.shuadantool.mapper")
public class ShuadanMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShuadanMonitorApplication.class, args);
    }
}
