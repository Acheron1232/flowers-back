package com.acheron.flowers.store.dto;

import com.acheron.flowers.store.entity.Category;
import com.acheron.flowers.store.entity.ProductImage;
import com.acheron.flowers.store.entity.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaveDto {
    private String uaName;
    private String enName;
    private String uaDescription;
    private String enDescription;
    private Integer price;
    private Long categoryId;
}
