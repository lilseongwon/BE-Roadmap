package com.example.smsexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SmsExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsExampleApplication.class, args);
    }

}
