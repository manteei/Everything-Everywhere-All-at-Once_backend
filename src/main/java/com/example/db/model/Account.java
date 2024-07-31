package com.example.db.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    private String login;

    @Column(columnDefinition = "VARCHAR(255)")
    private String nickname;
    @Column(columnDefinition = "integer default 0")
    private Integer friends;

    @OneToOne
    @JoinColumn(name = "login", referencedColumnName = "login", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

}
