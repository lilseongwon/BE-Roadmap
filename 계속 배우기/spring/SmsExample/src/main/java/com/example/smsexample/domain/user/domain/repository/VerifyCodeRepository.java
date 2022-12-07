package com.example.smsexample.domain.user.domain.repository;

import com.example.smsexample.domain.user.domain.VerifyCode;
import org.springframework.data.repository.CrudRepository;

public interface VerifyCodeRepository extends CrudRepository<VerifyCode, String> {
}
