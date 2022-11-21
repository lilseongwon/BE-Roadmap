package com.example.jwtexample.global.properties;

import com.example.jwtexample.global.security.jwt.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = {JwtProperties.class})
public class EnableConfigurationPropertiesConfig {
}