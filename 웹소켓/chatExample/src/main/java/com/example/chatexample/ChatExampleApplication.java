package com.example.chatexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationPropertiesScan
@SpringBootApplication
@EnableConfigurationProperties
public class ChatExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatExampleApplication.class, args);
    }

}
