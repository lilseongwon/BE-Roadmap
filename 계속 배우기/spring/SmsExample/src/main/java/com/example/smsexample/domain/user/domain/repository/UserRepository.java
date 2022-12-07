package com.example.smsexample.domain.user.domain.repository;

import com.example.smsexample.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByAccountId(String accountId);

    Optional<User> findByAccountId(String accountId);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
