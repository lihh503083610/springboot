package com.horizon.springboottransaction;

import com.horizon.springboottransaction.datasource.DBConfig01;
import com.horizon.springboottransaction.datasource.DBConfig02;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = { DBConfig01.class, DBConfig02.class})
@MapperScan("com.horizon.springboottransaction.mapper")
public class SpringboottransactionApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringboottransactionApplication.class, args);
    }
}
