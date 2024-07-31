package com.example.db.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "universal")
public class Universal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "universal_id")
    private Integer universal_id;
    @Column(name = "name", nullable = false)
    private String name;
    @Max(100)
    @Min(-100)
    @Column(name = "coordinateX", nullable = false)
    private float coordinateX;
    @Max(100)
    @Min(-100)
    @Column(name = "coordinateY", nullable = false)
    private float coordinateY;
    @Column(name = "distance", nullable = false)
    private float distance;




}
