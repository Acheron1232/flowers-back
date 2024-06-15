package com.acheron.flowers.store.schedule;

import com.acheron.flowers.store.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromotionScheduler {
    private final PromotionService promotionService;

    @Scheduled(fixedRate = 3600000)
    public void checkForExpiredPromotions() {
        promotionService.deleteExpiredPromotions();
    }
}