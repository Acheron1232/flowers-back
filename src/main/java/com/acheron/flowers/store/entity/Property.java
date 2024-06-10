package com.acheron.flowers.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uaKey;
    private String enKey;
    private String uaValue;
    private String enValue;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
}
