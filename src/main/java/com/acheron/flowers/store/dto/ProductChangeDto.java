package com.acheron.flowers.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductChangeDto {
    private Long id;
    private String uaName;
    private String enName;
    private String uaDescription;
    private String enDescription;
    private String uaContent;
    private String enContent;
    private BigDecimal priceUsd;
    private BigDecimal priceUah;
    private Long categoryId;
}
