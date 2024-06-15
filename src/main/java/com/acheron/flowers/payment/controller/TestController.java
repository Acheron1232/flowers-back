package com.acheron.flowers.payment.controller;

import com.liqpay.LiqPay;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final LiqPay liqPay;

    @GetMapping("/asd")
    private String asd(){
        Map params = new HashMap();
        params.put("amount", "1.50");
        params.put("action", "pay");
        params.put("currency", "USD");
        params.put("description", "description text");
        params.put("order_id", "order_id_1");
        params.put("sandbox", "1"); // enable the testing environment and card will NOT charged. If not set will be used property isCnbSandbox()
        String html = liqPay.cnb_form(params);
        return html;
    }
}
