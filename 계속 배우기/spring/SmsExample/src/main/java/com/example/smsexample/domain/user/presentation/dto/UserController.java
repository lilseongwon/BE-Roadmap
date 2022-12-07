package com.example.smsexample.domain.user.presentation.dto;

import com.example.smsexample.domain.user.presentation.dto.request.SendCodeRequest;
import com.example.smsexample.domain.user.presentation.dto.request.SignUpRequset;
import com.example.smsexample.domain.user.presentation.dto.request.VerifyCodeRequest;
import com.example.smsexample.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid SignUpRequset request) {
        userService.signUp(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/send")
    public void sendCode(@RequestBody @Valid SendCodeRequest request) {
        userService.sendCode(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/verify")
    public void verifyCode(@RequestBody @Valid VerifyCodeRequest request) {
        userService.verifyCode(request);
    }

}
