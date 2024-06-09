package com.acheron.flowers.store.service;

import com.acheron.flowers.store.entity.Product;
import com.acheron.flowers.store.entity.ProductImage;
import com.acheron.flowers.store.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ImageService imageService;
    private final ImageRepository imageRepository;

    public ResponseEntity<?> save(Product product, MultipartFile file) {
        try {


            String url = imageService.saveImage(file);
            imageRepository.save(new ProductImage(null, url, product));
            return ResponseEntity.ok(url);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
