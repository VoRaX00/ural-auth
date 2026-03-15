package ru.ural.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ural.api.AuthApi;
import ru.ural.dto.LoginDto;
import ru.ural.dto.RegistrationDto;
import ru.ural.dto.TokenRequest;
import ru.ural.dto.AuthDto;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    @Override
    public ResponseEntity<AuthDto> login(LoginDto loginDto) {
        return null;
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
