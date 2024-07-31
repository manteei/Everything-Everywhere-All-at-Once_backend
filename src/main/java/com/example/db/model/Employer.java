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
@Table(name = "employer")
@IdClass(EmployerId.class)
public class Employer {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)

    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "hero", referencedColumnName = "login")
    @OnDelete(action = OnDeleteAction.SET_NULL)
 //   @JsonIgnore
    private User hero;

    @Id
    @ManyToOne
    @JoinColumn(name = "coordinator", referencedColumnName = "login")
    @OnDelete(action = OnDeleteAction.SET_NULL)
  //  @JsonIgnore
    private User coordinator;
}
