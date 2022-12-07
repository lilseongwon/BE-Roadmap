package com.example.smsexample.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SignUpRequset {

    @NotBlank
    @Size(min = 6, max = 24)
    private String accountId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,24}$",
            message = "password는 영문자[a-z | A-Z], 숫자[0-9], 특수문자[!, @, #, $, %, ^, &, +, =]가 최소 1개씩 포함되어야 합니다.")
    private String password;

    @NotBlank
    @Size(min = 11, max = 11)
    private String phoneNumber;
}