package ru.ural.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ural.entities.Credential;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    Optional<Credential> findByUserId(Long userId);

}
