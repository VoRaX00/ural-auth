package ru.ural.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.ural.entities.User;
import ru.ural.models.UserModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(UserModel userModel);

    void mapUserModelToEntity(@MappingTarget User entity, UserModel userModel);

}
