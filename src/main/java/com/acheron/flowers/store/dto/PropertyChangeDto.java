package com.acheron.flowers.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyChangeDto {
    private Long id;
    private String uaKey;
    private String enKey;
    private String uaValue;
    private String enValue;
}
