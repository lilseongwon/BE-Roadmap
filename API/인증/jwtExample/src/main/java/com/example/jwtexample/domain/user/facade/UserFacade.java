package com.example.jwtexample.domain.user.facade;

import com.example.jwtexample.domain.user.domain.User;
import com.example.jwtexample.domain.user.domain.repository.UserRepository;
import com.example.jwtexample.domain.user.exception.PasswordMismatchException;
import com.example.jwtexample.domain.user.exception.UserExistException;
import com.example.jwtexample.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserFacade {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void checkUserExist(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent())
            throw UserExistException.EXCEPTION;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
    public void checkPassword(User user, String password) {
        if(!passwordEncoder.matches(user.getPassword(), password)) {
            throw PasswordMismatchException.EXCEPTION;
        }
    }

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(email);
    }
}