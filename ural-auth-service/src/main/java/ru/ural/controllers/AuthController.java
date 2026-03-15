package ru.ural.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ural.api.AuthApi;
import ru.ural.dto.LoginDto;
import ru.ural.dto.RegistrationDto;
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
    public ResponseEntity<AuthDto> registration(RegistrationDto registrationDto) {
        return null;
    }

    @Override
    public ResponseEntity<AuthDto> refreshTokens(TokenRequest tokenRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> logout() {
        return null;
    }

    @Override
    public ResponseEntity<Void> logoutAll() {
        return null;
    }
}
