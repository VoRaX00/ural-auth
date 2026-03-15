package ru.ural.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import ru.ural.enums.UserRole;
import ural.ru.entities.BaseEntity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private UserRole code;

    @Override
    public String getAuthority() {
        return code.name();
    }
}
