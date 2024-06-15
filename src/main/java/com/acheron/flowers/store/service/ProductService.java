package com.acheron.flowers.store.service;

import com.acheron.flowers.store.dto.ProductChangeDto;
import com.acheron.flowers.store.dto.ProductSaveDto;
import com.acheron.flowers.store.entity.Category;
import com.acheron.flowers.store.entity.Product;
import com.acheron.flowers.store.exception.CategoryNotFoundException;
import com.acheron.flowers.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    public ResponseEntity<?> save(ProductSaveDto productSaveDto) {
        try {
            Category category = categoryService.findById(productSaveDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException(productSaveDto.getCategoryId()));
            return ResponseEntity.ok(productRepository.save(
                    new Product(
                            null,
                            productSaveDto.getUaName(),
                            productSaveDto.getEnName(),
                            productSaveDto.getUaDescription(),
                            productSaveDto.getEnDescription(),
                            productSaveDto.getUaContent(),
                            productSaveDto.getEnContent(),
                            productSaveDto.getPriceUsd(),
                            productSaveDto.getPriceUah(),
                            category, null,null)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> save(ProductChangeDto productChangeDto) {
        if (productChangeDto.getId() != null && productRepository.existsById(productChangeDto.getId())) {
            Product product = productRepository.findById(productChangeDto.getId()).orElseThrow();//TODO
            try {
                return ResponseEntity.ok(
                        productRepository.
                                save(new Product(
                                        product.getId(),
                                        productChangeDto.getUaName() != null ? productChangeDto.getUaName() : product.getUaName(),
                                        productChangeDto.getEnName() != null ? productChangeDto.getEnName() : product.getEnName(),
                                        productChangeDto.getUaDescription() != null ? productChangeDto.getUaDescription() : product.getUaDescription(),
                                        productChangeDto.getEnDescription() != null ? productChangeDto.getEnDescription() : product.getEnDescription(),
                                        productChangeDto.getUaContent() != null ? productChangeDto.getUaContent() : product.getUaContent(),
                                        productChangeDto.getEnContent() != null ? productChangeDto.getEnContent() : product.getEnContent(),
                                        productChangeDto.getPriceUsd() != null ? productChangeDto.getPriceUsd() : product.getPriceUsd(),
                                        productChangeDto.getPriceUah() != null ? productChangeDto.getPriceUah() : product.getPriceUah(),
                                        productChangeDto.getCategoryId() != null ? categoryService.findById(productChangeDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException(productChangeDto.getId())) : product.getCategory(),
                                        product.getImages(),
                                        product.getProperties()
                                )));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else
            return ResponseEntity.badRequest().body("Product with id: " + productChangeDto.getId() + " does not exists");
    }

    public ResponseEntity<?> delete(Long id) {
        if (id != null && productRepository.existsById(id)) {
            try {
                productRepository.deleteById(id);
                return ResponseEntity.ok("Success");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("Product with id: " + id + " does not exists");
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }
}
