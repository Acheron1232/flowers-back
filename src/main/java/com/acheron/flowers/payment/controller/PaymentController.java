package com.acheron.flowers.payment.controller;

import com.acheron.flowers.payment.dto.PaymentRequestDto;
import com.acheron.flowers.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3")
public class PaymentController {
    private final PaymentService paymentService;


    @PostMapping("/payment")
    public ResponseEntity<?> generateAndSavePayment(@RequestBody PaymentRequestDto paymentRequestDto){
        return paymentService.generate(paymentRequestDto);
    }
}
