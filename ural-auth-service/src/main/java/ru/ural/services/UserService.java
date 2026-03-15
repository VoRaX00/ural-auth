package ru.ural.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ural.entities.User;
import ru.ural.repositories.UserRepository;
import ural.ru.exceptions.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUser(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UnauthorizedException("Invalid login or password"));
    }

}
