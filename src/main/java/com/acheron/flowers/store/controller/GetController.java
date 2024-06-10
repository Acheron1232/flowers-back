package com.acheron.flowers.store.controller;

import com.acheron.flowers.store.service.CategoryService;
import com.acheron.flowers.store.service.ProductImageService;
import com.acheron.flowers.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class GetController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductImageService imageService;

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@RequestParam(defaultValue = "0") Integer pageNumber,
                                         @RequestParam(defaultValue = "20") Integer pageSize) {
        return ResponseEntity.ok(productService.findAll(PageRequest.of(pageNumber, pageSize)));

    }
    @GetMapping("/categories")
    public ResponseEntity<?> getCategories(@RequestParam(defaultValue = "0") Integer pageNumber,
                                         @RequestParam(defaultValue = "20") Integer pageSize) {
        return ResponseEntity.ok(categoryService.findAll(PageRequest.of(pageNumber, pageSize)));

    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id){
        try{

            String url = imageService.findById(id).orElseThrow();
            return ResponseEntity.ok(url);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
