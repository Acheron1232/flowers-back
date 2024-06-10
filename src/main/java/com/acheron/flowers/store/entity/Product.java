package com.acheron.flowers.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductImage> images;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Property> properties;

}
