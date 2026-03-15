package ru.ural.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ural.entities.Credential;
import ru.ural.entities.User;
import ru.ural.models.AuthModel;
import ru.ural.models.LoginModel;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final CredentialService credentialService;

    private final TokenService tokenService;

    public AuthModel login(LoginModel loginModel) {
        User user = userService.findUser(loginModel.getLogin());
        Credential credential = credentialService.findByUserId(user.getId());

        credentialService.checkPassword(loginModel.getPassword(), credential);
        return tokenService.issueToken(user);
    }

}
