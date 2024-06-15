package com.acheron.flowers.store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uaName;
    private String enName;
    private String uaDescription;
    private String enDescription;
    private String uaContent;
    private String enContent;
    @Column(name = "price_usd", nullable = false)
    private BigDecimal priceUsd;
    @Column(name = "price_uah", nullable = false)
    private BigDecimal priceUah;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductImage> images;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Property> properties;
}
