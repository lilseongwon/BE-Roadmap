package com.example.jwtexample.domain.auth.presentation.dto;

import com.example.jwtexample.domain.auth.presentation.dto.response.TokenResponse;
import com.example.jwtexample.domain.auth.service.TokenRefreshService;
import com.example.jwtexample.domain.user.presentation.dto.request.UserSigninRequest;
import com.example.jwtexample.domain.auth.service.UserSigninService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserSigninService userSigninService;
    private final TokenRefreshService tokenRefreshService;

    @PostMapping("/auth")
    public TokenResponse signIn(@RequestBody UserSigninRequest request) {
        return userSigninService.execute(request);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)

    @PatchMapping("/auth")
    public TokenResponse tokenRefresh(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return tokenRefreshService.execute(refreshToken);
    }
}
