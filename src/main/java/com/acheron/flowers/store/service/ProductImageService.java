package com.acheron.flowers.store.service;

import com.acheron.flowers.store.entity.Product;
import com.acheron.flowers.store.entity.ProductImage;
import com.acheron.flowers.store.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final ProductService productService;

    public ResponseEntity<?> save(Long productId, MultipartFile file) {
        if (productId != null && productService.existsById(productId)) {
            Product product = productService.findById(productId).orElseThrow();
            try {
                String url = imageService.saveImage(file);
                imageRepository.save(new ProductImage(null, url, product));
                return ResponseEntity.ok(url);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("Product with id: " + productId + " does not exists");
    }

    public ResponseEntity<?> delete(Long id) {
        if (id != null && imageRepository.existsById(id)) {
            try {
                imageRepository.deleteById(id);
                return ResponseEntity.ok("Success");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else return ResponseEntity.badRequest().body("Image with id: " + id + " does not exists");
    }

    public Optional<String> findById(Long id) {
        return imageRepository.findById(id).map(ProductImage::getImage);
    }
}
