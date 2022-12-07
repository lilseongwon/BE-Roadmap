package com.example.smsexample.domain.user.domain;

import com.example.smsexample.global.enums.CodeType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("VerifyCode")
@Builder
public class VerifyCode {

    @Id
    private String phoneNumber;

    private String code;

    private Integer count;

    private CodeType type;

    @TimeToLive
    private long ttl;
}