package com.example.smsexample.domain.user.service;

import com.example.smsexample.domain.user.domain.User;
import com.example.smsexample.domain.user.domain.VerifyCode;
import com.example.smsexample.domain.user.domain.repository.UserRepository;
import com.example.smsexample.domain.user.domain.repository.VerifyCodeRepository;
import com.example.smsexample.domain.user.exception.*;
import com.example.smsexample.domain.user.presentation.dto.request.SendCodeRequest;
import com.example.smsexample.domain.user.presentation.dto.request.SignUpRequset;
import com.example.smsexample.domain.user.presentation.dto.request.VerifyCodeRequest;
import com.example.smsexample.infrastructure.utils.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerifyCodeRepository verifyCodeRepository;
    private final MessageUtil messageUtil;

    @Transactional
    public void signUp(SignUpRequset request) {
        if (userRepository.existsByAccountId(request.getAccountId())) {
            throw UserExistsException.EXCEPTION;
        }

        User user = User.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .build();
        userRepository.save(user);
    }
    public void sendCode(SendCodeRequest request) {
        int count = verifyCodeRepository.findById(request.getPhoneNumber()).isEmpty() ?
                0 : verifyCodeRepository.findById(request.getPhoneNumber()).get().getCount();

        if (count >= 5) {
            throw TooManySendCodeException.EXCEPTION;
        }

        messageUtil.send(request.getPhoneNumber(), count, request.getType());
    }

    public void verifyCode(VerifyCodeRequest request) {
        VerifyCode verifyCode = verifyCodeRepository.findById(request.getPhoneNumber())
                .orElseThrow(() -> VerifyCodeExpiredException.EXCEPTION);

        if (!verifyCode.getCode().equals(request.getCode())) {
            throw VerifyCodeDifferentException.EXCEPTION;
        }

        if (!verifyCode.getType().equals(request.getType())) {
            throw InvalidCodeTypeException.EXCEPTION;
        }
    }
}