package com.example.smsexample.domain.user.presentation.dto.request;

import com.example.smsexample.global.enums.CodeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SendCodeRequest {

    @NotBlank
    @Size(min = 11, max = 11)
    private String phoneNumber;

    @NotNull
    private CodeType type;
}