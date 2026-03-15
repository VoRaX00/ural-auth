package ru.ural.services;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import ru.ural.entities.RefreshToken;
import ru.ural.entities.Role;
import ru.ural.entities.User;
import ru.ural.enums.ClaimKey;
import ru.ural.enums.UserRole;
import ru.ural.models.AuthModel;
import ru.ural.models.UserPrincipals;
import ru.ural.properties.AuthProperty;
import ru.ural.properties.JwtProperty;
import ru.ural.repositories.RefreshTokenRepository;

import java.security.PrivateKey;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final AuthProperty authProperty;

    private final JwtProperty jwtProperty;

    private final PrivateKey privateKey;

    public AuthModel issueToken(@NonNull User user) {
        UserPrincipals userPrincipals = buildPrincipals(user);

        String accessJti = UUID.randomUUID().toString();
        String refreshJti = UUID.randomUUID().toString();

        var now = ZonedDateTime.now();
        ZonedDateTime expiredAtRefreshToken = now.plusSeconds(authProperty.getRefreshTokenExpiredOnSeconds());
        String accessToken = generateAccessToken(userPrincipals, accessJti, now);
        String refreshToken = generateRefreshToken(userPrincipals, refreshJti, now, expiredAtRefreshToken);

        RefreshToken token = RefreshToken.builder()
                .token(refreshToken)
                .issuedAt(now)
                .accessJti(accessJti)
                .refreshJti(refreshJti)
                .user(user)
                .expiredAt(expiredAtRefreshToken)
                .build();

        refreshTokenRepository.save(token);
        return AuthModel.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private UserPrincipals buildPrincipals(@NonNull User user) {
        Set<UserRole> roles = user.getRoles().stream()
                .map(Role::getCode)
                .collect(Collectors.toSet());

        return UserPrincipals.builder()
                .id(user.getId())
                .email(user.getEmail())
                .roles(roles)
                .build();
    }

    private String generateAccessToken(
            @NonNull UserPrincipals principals,
            @NonNull String jti,
            @NonNull ZonedDateTime issueAt
    ) {
        ZonedDateTime expiredAt = issueAt.plusSeconds(authProperty.getAccessTokenExpiredOnSeconds());
        return Jwts.builder()
                .setId(jti)
                .setSubject(principals.getId().toString())
                .setIssuedAt(Date.from(issueAt.toInstant()))
                .setExpiration(Date.from(expiredAt.toInstant()))
                .claim(ClaimKey.USER_ID_KEY.getKey(), principals.getId())
                .claim(ClaimKey.ROLES_KEY.getKey(), principals.getRoles())
                .claim(ClaimKey.EMAIL_KEY.getKey(), principals.getEmail())
                .signWith(privateKey)
                .compact();
    }

    private String generateRefreshToken(
            @NonNull UserPrincipals principals,
            @NonNull String jti,
            @NonNull ZonedDateTime issueAt,
            @NonNull ZonedDateTime expiredAt
    ) {
        return Jwts.builder()
                .setId(jti)
                .setSubject(principals.getId().toString())
                .setIssuedAt(Date.from(issueAt.toInstant()))
                .setExpiration(Date.from(expiredAt.toInstant()))
                .claim(ClaimKey.USER_ID_KEY.getKey(), principals.getId())
                .signWith(privateKey)
                .compact();
    }

}
