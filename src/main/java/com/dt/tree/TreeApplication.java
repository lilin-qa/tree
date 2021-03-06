package com.dt.tree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("com.dt.tree.dao")//非常重要
public class TreeApplication {

    public static void main(String[] args) {

        SpringApplication.run(TreeApplication.class, args);
    }

}
