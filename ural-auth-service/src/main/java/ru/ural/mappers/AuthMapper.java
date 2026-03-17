package ru.ural.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.ural.dto.AuthDto;
import ru.ural.dto.LoginDto;
import ru.ural.dto.UserDto;
import ru.ural.models.AuthModel;
import ru.ural.models.LoginModel;
import ru.ural.models.UserModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {

    LoginModel toModel(LoginDto loginDto);

    AuthDto toDto(AuthModel authModel);

    UserModel toModel(UserDto userDto);

}
