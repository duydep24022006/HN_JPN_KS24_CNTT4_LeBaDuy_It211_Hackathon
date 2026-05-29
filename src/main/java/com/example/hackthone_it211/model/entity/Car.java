package com.example.hackthone_it211.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="car")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String brand;
    private Double price;
    @Enumerated(EnumType.STRING)
    private CarStatus status;
    @Column(name = "is_deleted", nullable = false)
    private boolean is_deleted=false;
}
