package com.acheron.flowers.payment.config;

import com.liqpay.LiqPay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiqPayCustomConfig {
    @Value("${liqpay.public_key}")
    private String publicKey;
    @Value("${liqpay.private_key}")
    private String privateKey;
    @Bean
    public LiqPay liqPayConfig(){
        return new LiqPay(publicKey, privateKey);
    }
}
