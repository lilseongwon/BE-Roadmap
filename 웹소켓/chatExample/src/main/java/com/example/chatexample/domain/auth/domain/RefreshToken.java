package com.example.chatexample.domain.auth.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash
public class RefreshToken {
    @Id
    private String email;

    @Indexed
    private String token;

    @TimeToLive
    private Long expiration;

    public void updateToken(String token, Long expiration) {
        this.token = token;
        this.expiration = expiration;
    }
}