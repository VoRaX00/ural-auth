package ru.ural.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ural.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query("""
        select r
        from RefreshToken r
        left join fetch r.user
        where r.refreshJti = ?1
    """)
    Optional<RefreshToken> findByRefreshJti(String refreshJti);

    void deleteByRefreshJti(String refreshJti);

    void deleteByAccessJti(String accessJti);

    void deleteAllByUserId(Long userId);

}
