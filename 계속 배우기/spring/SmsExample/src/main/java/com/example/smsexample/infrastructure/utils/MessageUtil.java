package com.example.smsexample.infrastructure.utils;

import com.example.smsexample.domain.user.domain.VerifyCode;
import com.example.smsexample.domain.user.domain.repository.VerifyCodeRepository;
import com.example.smsexample.domain.user.exception.SendCodeErrorException;
import com.example.smsexample.global.enums.CodeType;
import com.example.smsexample.infrastructure.CoolsmsProperties;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class MessageUtil {

    private final CoolsmsProperties coolsmsProperties;
    private final VerifyCodeRepository verifyCodeRepository;

    public void send(String phoneNumber, Integer count, CodeType type) {
        try {
            Message sms = new Message(coolsmsProperties.getApiKey(), coolsmsProperties.getApiSecret());

            StringBuilder code = new StringBuilder();
            SecureRandom random = new SecureRandom();

            for (int i = 0; i < 4; i++) {
                code.append(random.nextInt(10));
            }

            HashMap<String, String> params = new HashMap<>();
            params.put("to", phoneNumber);
            params.put("from", coolsmsProperties.getSender());
            params.put("type", "SMS");
            params.put("text", "인증번호는 [" + code + "] 입니다.");

            sms.send(params);

            VerifyCode verifyCode = VerifyCode.builder()
                    .phoneNumber(phoneNumber)
                    .code(code.toString())
                    .count(count + 1)
                    .type(type)
                    .ttl(300)
                    .build();

            verifyCodeRepository.save(verifyCode);
        } catch (CoolsmsException e) {
            throw SendCodeErrorException.EXCEPTION;
        }
    }
}