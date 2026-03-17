package ru.ural.services;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.ural.entities.Credential;
import ru.ural.entities.User;
import ru.ural.models.AuthModel;
import ru.ural.models.LoginModel;
import ru.ural.models.UserModel;
import ru.ural.utils.JwtUtils;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final CredentialService credentialService;

    private final TokenService tokenService;

    public AuthModel login(@NonNull LoginModel loginModel) {
        User user = userService.findUser(loginModel.getLogin());
        Credential credential = credentialService.findByUserId(user.getId());

        credentialService.checkPassword(loginModel.getPassword(), credential);
        return tokenService.issueToken(user);
    }

    public AuthModel registration(@NonNull UserModel userModel) {
        userService.checkDuplicate(userModel);
        var user = userService.createUser(userModel);
        return tokenService.issueToken(user);
    }

    public AuthModel refreshTokens(@NonNull String refreshToken) {
        return tokenService.refreshTokens(refreshToken);
    }

    public void logout(Authentication authentication) {
        Jwt accessToken = JwtUtils.getToken(authentication);
        tokenService.deleteRefreshTokenByAccess(accessToken);
    }

    public void logoutAll(Authentication authentication) {
        Jwt accessToken = JwtUtils.getToken(authentication);
        tokenService.deleteAllRefreshTokensByAccess(accessToken);
    }

}
