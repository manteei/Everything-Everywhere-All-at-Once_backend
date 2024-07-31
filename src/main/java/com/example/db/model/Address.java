package com.example.db.model;

import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "universal_id", nullable = false)
    private Universal universal_id;
    @Column(name = "longitude", nullable = false)
    private float longitude;
    @Column(name = "latitude", nullable = false)
    private float latitude;

}
