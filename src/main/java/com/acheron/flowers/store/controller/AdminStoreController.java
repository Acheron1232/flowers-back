package com.acheron.flowers.store.controller;

import com.acheron.flowers.store.dto.*;
import com.acheron.flowers.store.service.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/admin/")
@RequiredArgsConstructor
public class AdminStoreController {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductImageService productImageService;
    private final PropertyService propertyService;
    private final PromotionService promotionService;

    //category
    @PostMapping("/category")
    public ResponseEntity<?> saveCategory(@RequestBody CategorySaveDto categorySaveDto) {
        return categoryService.save(categorySaveDto);
    }

    @PutMapping("/category")
    public ResponseEntity<?> changeCategory(@RequestBody CategoryChangeDto categoryChangeDto) {
        return categoryService.save(categoryChangeDto);
    }

    @DeleteMapping("/category")
    public ResponseEntity<?> deleteCategory(@RequestBody ObjectNode objectNode) {
        try {
            return categoryService.delete(objectNode.get("id").asLong());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Id is not correct");
        }
    }

    //product
    @PostMapping("/product")
    public ResponseEntity<?> saveProduct(@RequestBody ProductSaveDto productSaveDto){
        return productService.save(productSaveDto);
    }

    @PutMapping("/product")
    public ResponseEntity<?> changeProduct(@RequestBody ProductChangeDto productChangeDto){
        return productService.save(productChangeDto);
    }

    @DeleteMapping("/product")
    public ResponseEntity<?> deleteProduct(@RequestBody ObjectNode objectNode){
        try {
            return productService.delete(objectNode.get("id").asLong());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Id is not correct");
        }
    }

    //image

    @PostMapping("/image/{id}")
    public ResponseEntity<?> saveImage(@PathVariable Long id, @RequestPart MultipartFile file){
        return productImageService.save(id,file);
    }

    @DeleteMapping("/image")
    public ResponseEntity<?> deleteImage(@RequestBody ObjectNode objectNode){
        try {
            return productImageService.delete(objectNode.get("id").asLong());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Id is not correct");
        }
    }

    //property

    @PostMapping("/property")
    public ResponseEntity<?> saveProperty(@RequestBody PropertySaveDto propertySaveDto){
        return propertyService.save(propertySaveDto);
    }

    @PutMapping("/property")
    public ResponseEntity<?> changeProperty(@RequestBody PropertyChangeDto propertyChangeDto){
        return propertyService.save(propertyChangeDto);
    }

    @DeleteMapping("/property")
    public ResponseEntity<?> deleteProperty(@RequestBody ObjectNode objectNode){
        try {
            return propertyService.delete(objectNode.get("id").asLong());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Id is not correct");
        }
    }

    //promotion

    @PostMapping("/promotion")
    public ResponseEntity<?> savePromotion(@RequestBody PromotionSaveDto promotionSaveDto){
        return promotionService.save(promotionSaveDto);
    }
}
