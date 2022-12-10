package com.example.smsexample.infrastructure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "coolsms")
@RequiredArgsConstructor
public class CoolsmsProperties {

    private final String apiKey;
    private final String apiSecret;
    private final String senderNumber;
}
