package com.application.springConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConfigExample {
    /*public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(com.application.springConfig.ConfigExample.class, args);
        AppConfiguration appConfiguration = context.getBean(AppConfiguration.class);

        System.out.println(appConfiguration.getServerPort());
        System.out.println(appConfiguration.getUrl());
    }*/
}

