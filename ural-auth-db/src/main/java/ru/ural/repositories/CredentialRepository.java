package ru.ural.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ural.entities.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
}
