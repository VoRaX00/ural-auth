package ru.ural.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String login;

    private String email;

    private String lastName;

    private String firstName;

    private String patronymic;

    private String password;

}
