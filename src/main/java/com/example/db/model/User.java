package com.example.db.model;


import lombok.*;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private String login;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "surname", length = 20, nullable = false)
    private String surname;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AuthorizationData authorizationData;


}
