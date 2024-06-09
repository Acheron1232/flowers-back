package com.acheron.flowers.store.controller;

import com.acheron.flowers.store.dto.CategoryChangeDto;
import com.acheron.flowers.store.dto.CategorySaveDto;
import com.acheron.flowers.store.dto.ProductChangeDto;
import com.acheron.flowers.store.dto.ProductSaveDto;
import com.acheron.flowers.store.entity.Category;
import com.acheron.flowers.store.service.CategoryService;
import com.acheron.flowers.store.service.ProductService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/")
@RequiredArgsConstructor
public class AdminStoreController {
    private final CategoryService categoryService;
    private final ProductService productService;

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
            return ResponseEntity.badRequest().body("id is not correct");
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
            return ResponseEntity.badRequest().body("id is not correct");
        }
    }
}
