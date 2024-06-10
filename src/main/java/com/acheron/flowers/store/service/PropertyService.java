package com.acheron.flowers.store.service;

import com.acheron.flowers.store.dto.PropertyChangeDto;
import com.acheron.flowers.store.dto.PropertySaveDto;
import com.acheron.flowers.store.entity.Product;
import com.acheron.flowers.store.entity.Property;
import com.acheron.flowers.store.exception.ProductNotFoundException;
import com.acheron.flowers.store.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final ProductService productService;

    public ResponseEntity<?> save(PropertySaveDto propertySaveDto) {
        try {
            Product product = productService.findById(propertySaveDto.getProductId()).orElseThrow(() -> new ProductNotFoundException(propertySaveDto.getProductId()));
            return ResponseEntity.ok(propertyRepository.save(
                    new Property(
                            null,
                            propertySaveDto.getUaKey(),
                            propertySaveDto.getEnKey(),
                            propertySaveDto.getUaValue(),
                            propertySaveDto.getEnValue(),
                            product
                    )));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> save(PropertyChangeDto propertyChangeDto) {
        if (propertyChangeDto.getId() != null && propertyRepository.existsById(propertyChangeDto.getId())) {
            Property property = propertyRepository.findById(propertyChangeDto.getId()).orElseThrow(); //TODO
            try {
                return ResponseEntity.ok(
                        propertyRepository.
                                save(new Property(
                                        property.getId(),
                                        propertyChangeDto.getUaKey()!=null? propertyChangeDto.getUaKey() : property.getUaKey(),
                                        propertyChangeDto.getEnKey()!=null? propertyChangeDto.getEnKey() : property.getEnKey(),
                                        propertyChangeDto.getUaValue()!=null? propertyChangeDto.getUaValue() : property.getUaValue(),
                                        propertyChangeDto.getEnValue()!=null? propertyChangeDto.getEnValue() : property.getEnValue(),
                                        property.getProduct()
                                )));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else
            return ResponseEntity.badRequest().body("Property with id: " + propertyChangeDto.getId() + " does not exists");
    }

    public ResponseEntity<?> delete(Long id) {
        if (id != null && propertyRepository.existsById(id)) {
            try {
                propertyRepository.deleteById(id);
                return ResponseEntity.ok("Success");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("Property with id: " + id + " does not exists");
    }
}
