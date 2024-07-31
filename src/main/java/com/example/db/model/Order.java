package com.example.db.model;
import com.example.db.model.enums.StatusOrder;
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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "incident_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private IncidentInformation incidentInformation;

    @Enumerated(EnumType.STRING)
    private StatusOrder status;
}
