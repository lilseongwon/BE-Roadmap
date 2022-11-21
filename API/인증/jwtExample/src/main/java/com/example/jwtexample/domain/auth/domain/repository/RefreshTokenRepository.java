package com.example.jwtexample.domain.auth.domain.repository;

import com.example.jwtexample.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String RefreshToken);
}