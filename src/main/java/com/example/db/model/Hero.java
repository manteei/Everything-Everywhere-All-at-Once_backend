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
@Table(name = "hero")
public class Hero {
    @Id
    private String login;
    @Column(columnDefinition = "integer default 0")
    private Integer skill;

    @OneToOne
    @JoinColumn(name = "login", referencedColumnName = "login")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
