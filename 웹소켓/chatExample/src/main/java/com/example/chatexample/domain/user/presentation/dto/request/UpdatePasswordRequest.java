package com.example.chatexample.domain.user.presentation.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank(message = "old_passwod는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    private String oldPassword;

    @NotBlank(message = "new_password는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    private String newPassword;
}