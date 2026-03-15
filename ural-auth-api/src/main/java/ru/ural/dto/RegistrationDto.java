package ru.ural.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto для регистрации")
public class RegistrationDto {

    private String login;

    private String email;

    private String lastName;

    private String firstName;

    private String patronymic;

    private String password;

}
