package com.acheron.flowers.store.repository;

import com.acheron.flowers.store.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByDateBefore(LocalDateTime now);
//    List<Promotion> findByEndDateBefore(Date date);
}