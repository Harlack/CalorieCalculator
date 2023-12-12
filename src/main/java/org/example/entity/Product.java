package org.example.entity;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String name;
    @Column(nullable=false)
    private Integer calories;
    @Column(nullable=false)
    private Integer totalFat;
    private Integer cholesterol;
    private Integer sodium;
    private Integer totalCarbohydrates;
    private Integer protein;
}
