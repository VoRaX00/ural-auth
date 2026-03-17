package ru.ural.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import ru.ural.entities.Credential;
import ru.ural.entities.User;
import ru.ural.repositories.CredentialRepository;
import ru.ural.utils.AuthUtils;
import ru.ural.exceptions.InternalServerException;
import ru.ural.exceptions.UnauthorizedException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialRepository credentialRepository;

    @NonNull
    public Credential findByUserId(@NonNull Long userId) {
        return credentialRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.error("Not found salt by userId: {}", userId);
                    return new InternalServerException("Not found salt");
                });
    }

    public void checkPassword(@NonNull String password, @NonNull Credential credential) {
        String hashPassword = AuthUtils.hashPassword(password, credential.getSalt());
        if (!credential.getHash().equals(hashPassword)) {
            throw new UnauthorizedException("Invalid login or password");
        }
    }

    public void saveCredentials(User user, String hashPassword, String salt) {
        Credential credential = Credential.builder()
                .user(user)
                .hash(hashPassword)
                .salt(salt)
                .build();

        credentialRepository.save(credential);
    }

}
