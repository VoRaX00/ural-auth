package ru.ural.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import ru.ural.api.AuthApi;
import ru.ural.dto.LoginDto;
import ru.ural.dto.UserDto;
import ru.ural.dto.TokenRequest;
import ru.ural.dto.AuthDto;
import ru.ural.mappers.AuthMapper;
import ru.ural.services.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    private final AuthMapper authMapper;

    @Override
    public ResponseEntity<AuthDto> login(LoginDto loginDto) {
        var loginModel = authMapper.toModel(loginDto);
        var authModel = authService.login(loginModel);
        return ResponseEntity.ok(authMapper.toDto(authModel));
    }

    @Override
    public ResponseEntity<AuthDto> registration(UserDto userDto) {
        var user = authMapper.toModel(userDto);
        var authModel = authService.registration(user);
        return ResponseEntity.ok(authMapper.toDto(authModel));
    }

    @Override
    public ResponseEntity<AuthDto> refreshTokens(TokenRequest tokenRequest) {
        var authModel = authService.refreshTokens(tokenRequest.getRefreshToken());
        return ResponseEntity.ok(authMapper.toDto(authModel));
    }

    @Override
    public ResponseEntity<Void> logout(Authentication authentication) {
        authService.logout(authentication);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> logoutAll(Authentication authentication) {
        authService.logoutAll(authentication);
        return null;
    }
}
