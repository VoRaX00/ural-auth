package ru.ural.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ural.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
