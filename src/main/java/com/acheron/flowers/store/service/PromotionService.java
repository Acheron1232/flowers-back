package com.acheron.flowers.store.service;

import com.acheron.flowers.store.dto.PromotionSaveDto;
import com.acheron.flowers.store.entity.Category;
import com.acheron.flowers.store.entity.Product;
import com.acheron.flowers.store.entity.Promotion;
import com.acheron.flowers.store.exception.CategoryNotFoundException;
import com.acheron.flowers.store.exception.ProductNotFoundException;
import com.acheron.flowers.store.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {
    private final PromotionRepository promotionRepository;
    private final ProductService productService;

    public ResponseEntity<?> save(PromotionSaveDto promotionSaveDto) {
        try {
            Product product = productService.findById(promotionSaveDto.getProductId()).orElseThrow(() -> new ProductNotFoundException(promotionSaveDto.getProductId()));
            return ResponseEntity.ok(promotionRepository.save(
                    new Promotion(
                            null,
                            promotionSaveDto.getDiscountPrice(),
                            promotionSaveDto.getDate(),
                            product
                    )));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    public void deleteExpiredPromotions() {
        List<Promotion> expiredPromotions = promotionRepository.findByDateBefore(LocalDateTime.now());
        promotionRepository.deleteAll(expiredPromotions);
    }
}