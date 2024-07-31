package com.example.db.model;
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
@Table(name = "incidentInformation")
public class IncidentInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "monster_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Monster monster;

    @ManyToOne
    @JoinColumn(name = "login", referencedColumnName = "login")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user;
}
