package com.example.db.model;




import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monsters")
public class Monster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Max(1000)
    @Min(1)
    @Column(name = "price", nullable = false)
    private double price;
    @Max(10)
    @Min(1)
    @Column(name = "level", nullable = false)
    private int level;
}
