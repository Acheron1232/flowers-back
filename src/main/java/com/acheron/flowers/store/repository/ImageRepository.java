package com.acheron.flowers.store.repository;

import com.acheron.flowers.store.entity.Category;
import com.acheron.flowers.store.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ProductImage,Long> {
}
