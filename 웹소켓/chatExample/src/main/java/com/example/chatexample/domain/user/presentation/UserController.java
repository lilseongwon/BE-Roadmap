package com.example.chatexample.domain.user.presentation;

import com.example.chatexample.domain.auth.presentation.dto.response.TokenResponse;
import com.example.chatexample.domain.user.presentation.dto.request.*;
import com.example.chatexample.domain.user.presentation.dto.response.QueryUserInfoResponse;
import com.example.chatexample.domain.user.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {


    private final SignupService signupService;
    private final LoginService loginService;
    private final TokenRefreshService tokenRefreshService;

    private final QueryMyInfoService queryMyInfoService;
    private final QueryUserInfoService queryUserInfoService;

    private final UpdateUserInfoService updateUserInfoService;
    private final UpdatePasswordService updatePasswordService;
    private final LogoutService logoutService;
    private final WithdrawalService withdrawalService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TokenResponse signup(@RequestBody @Valid SignupRequest request){
        return signupService.execute(request);
    }

    @PostMapping("/auth")
    public TokenResponse login(@RequestBody @Valid LoginRequest request){
        return loginService.execute(request);
    }

    @PutMapping("/auth")
    public TokenResponse tokenRefresh(@RequestHeader("X-Refresh-Token") String refreshToken){
        return tokenRefreshService.execute(refreshToken);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateUserInfo(@RequestBody @Valid UpdateUserInfoRequest request) {
        updateUserInfoService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updateUserPassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }

    @GetMapping
    public QueryUserInfoResponse queryMyInfo() {
        return queryMyInfoService.execute();
    }

    @GetMapping("/{user-id}")
    public QueryUserInfoResponse queryUserInfo(@PathVariable("user-id") Long userId) {
        return queryUserInfoService.execute(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/logout")
    public void logout(){
        logoutService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void withdrawal(@RequestBody @Valid WithdrawalRequest request){
        withdrawalService.execute(request);
    }
}