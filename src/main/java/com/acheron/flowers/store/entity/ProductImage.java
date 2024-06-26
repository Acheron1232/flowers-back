package com.acheron.flowers.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_image")
@Builder
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String image;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
}
