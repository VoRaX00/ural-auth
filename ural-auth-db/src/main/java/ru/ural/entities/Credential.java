package ru.ural.entities;

import jakarta.persistence.*;
import lombok.*;
import ural.ru.entities.BaseEntity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credentials")
public class Credential extends BaseEntity {

    @Column(nullable = false)
    private String salt;

    @Column(nullable = false)
    private String hash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
