package ru.ural.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClaimKey {

    USER_ID_KEY("user_id"),
    ROLES_KEY("roles"),
    EMAIL_KEY("email");

    private final String key;

}
