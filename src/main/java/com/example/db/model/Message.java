package com.example.db.model;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "login1", referencedColumnName = "login")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "login2", referencedColumnName = "login")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User receiver;

    private Timestamp time;

    private String content;
}
