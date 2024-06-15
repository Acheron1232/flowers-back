package com.acheron.flowers.store.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionSaveDto {
    private Integer discountPrice;
    private LocalDateTime date;
    private Long productId;
}
