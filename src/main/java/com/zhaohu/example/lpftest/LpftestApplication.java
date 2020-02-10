package com.zhaohu.example.lpftest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//由于刚开始搭建环境的时候没有数据库资源，也没有配置进去，所以报错，因此要启动时忽略掉datasource的自动配置
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class LpftestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LpftestApplication.class, args);
    }

}
