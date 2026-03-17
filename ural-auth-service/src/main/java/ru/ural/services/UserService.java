package ru.ural.services;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import ru.ural.entities.Role;
import ru.ural.entities.User;
import ru.ural.enums.UserRole;
import ru.ural.mappers.UserMapper;
import ru.ural.models.UserModel;
import ru.ural.repositories.RoleRepository;
import ru.ural.repositories.UserRepository;
import ru.ural.utils.AuthUtils;
import ural.ru.exceptions.ConflictException;
import ural.ru.exceptions.InternalServerException;
import ural.ru.exceptions.UnauthorizedException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CredentialService credentialService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    @NonNull
    public User findUser(@NonNull String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UnauthorizedException("Invalid login or password"));
    }

    public void checkDuplicate(@NonNull UserModel user) {
        boolean existsDuplicate = userRepository.existsByLoginOrEmail(user.getLogin(), user.getEmail());
        if (existsDuplicate) {
            throw new ConflictException(String.format(
                    "User with login %s or email %s - already exists",
                    user.getLogin(), user.getEmail()
            ));
        }
    }

    @NonNull
    public User createUser(@NonNull UserModel userModel) {
        User user = userMapper.toEntity(userModel);

        Role userRole = roleRepository.findByCode(UserRole.USER.name())
                .orElseThrow(() -> new InternalServerException("Not found role: USER"));

        user.setRoles(Set.of(userRole));

        String salt = AuthUtils.generateSalt();
        String hashPassword = AuthUtils.hashPassword(userModel.getPassword(), salt);

        var savedUser = userRepository.save(user);

        credentialService.saveCredentials(user, hashPassword, salt);
        return savedUser;
    }

}
