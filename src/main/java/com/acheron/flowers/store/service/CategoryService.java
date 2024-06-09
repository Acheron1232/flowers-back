package com.acheron.flowers.store.service;

import com.acheron.flowers.store.dto.CategoryChangeDto;
import com.acheron.flowers.store.dto.CategorySaveDto;
import com.acheron.flowers.store.entity.Category;
import com.acheron.flowers.store.repository.CategoryRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> save(CategorySaveDto categorySaveDto) {
        try {
            return ResponseEntity.ok(categoryRepository.save(new Category(null, categorySaveDto.getUaName(),
                    categorySaveDto.getEnName(), null)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    public ResponseEntity<?> save(CategoryChangeDto categoryChangeDto) {
        if (categoryChangeDto.getId() != null && categoryRepository.existsById(categoryChangeDto.getId())) {
            Category category = categoryRepository.findById(categoryChangeDto.getId()).orElseThrow(); //TODO
            try {

                return ResponseEntity.ok(
                        categoryRepository.
                                save(new Category(
                                        categoryChangeDto.getId(),
                                        categoryChangeDto.getUaName() != null ? categoryChangeDto.getUaName() : category.getUaName(),
                                        categoryChangeDto.getEnName() != null ? categoryChangeDto.getEnName() : category.getEnName(),
                                        category.getProducts())));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else
            return ResponseEntity.badRequest().body("Category with id: " + categoryChangeDto.getId() + " not exists");
    }


    public ResponseEntity<?> delete(Long id) {
        if (id != null && categoryRepository.existsById(id)) {
            try {
                categoryRepository.deleteById(id);
                return ResponseEntity.ok("Success");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("Category with id: " + id + " not exists");
    }

    public List<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).getContent();
    }

    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
