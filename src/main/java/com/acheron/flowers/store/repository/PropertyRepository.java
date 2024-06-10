package com.acheron.flowers.store.repository;

import com.acheron.flowers.store.entity.Category;
import com.acheron.flowers.store.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {
}
