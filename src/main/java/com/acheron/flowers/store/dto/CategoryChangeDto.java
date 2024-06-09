package com.acheron.flowers.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryChangeDto {
    private Long id;
    private String uaName;
    private String enName;
}
