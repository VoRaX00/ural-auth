package ru.ural.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ural.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
