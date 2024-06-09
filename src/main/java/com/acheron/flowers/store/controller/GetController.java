package com.acheron.flowers.store.controller;

import com.acheron.flowers.store.service.CategoryService;
import com.acheron.flowers.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class GetController {

    private final ProductService productService;
    private final CategoryService categoryService;

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


}
