package ru.ural.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ural.dto.LoginDto;
import ru.ural.dto.RegistrationDto;
import ru.ural.dto.TokenRequest;
import ru.ural.dto.AuthDto;

@RequestMapping("/api/auth")
@Tag(name = "Контроллер для Аутентификации")
public interface AuthApi {

    @PostMapping("/login")
    ResponseEntity<AuthDto> login(@RequestBody LoginDto loginDto);

    @PostMapping("/registration")
    ResponseEntity<AuthDto> registration(@RequestBody RegistrationDto registrationDto);

    @PostMapping("/refresh-tokens")
    ResponseEntity<AuthDto> refreshTokens(@RequestBody TokenRequest tokenRequest);

    @PostMapping("/logout")
    ResponseEntity<Void> logout();

    @PostMapping("/logout/all")
    ResponseEntity<Void> logoutAll();

}
