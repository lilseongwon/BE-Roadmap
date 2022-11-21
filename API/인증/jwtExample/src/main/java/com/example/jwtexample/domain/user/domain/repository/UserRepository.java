package com.example.jwtexample.domain.user.domain.repository;

import com.example.jwtexample.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String accountId);
    List<User> findAllByOrderById();
}