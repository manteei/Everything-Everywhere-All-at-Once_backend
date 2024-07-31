package com.example.db.model;
import com.example.db.model.enums.StatusFriends;
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
@Table(name = "friends")
@IdClass(FriendsId.class)
public class Friends {
    @Id
    @ManyToOne
    @JoinColumn(name = "login1", referencedColumnName = "login")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user1;

    @Id
    @ManyToOne
    @JoinColumn(name = "login2", referencedColumnName = "login")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user2;

    @Enumerated(EnumType.STRING)
    private StatusFriends status;
}
