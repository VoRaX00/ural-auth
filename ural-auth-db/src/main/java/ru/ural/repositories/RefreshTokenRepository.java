package ru.ural.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ural.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
