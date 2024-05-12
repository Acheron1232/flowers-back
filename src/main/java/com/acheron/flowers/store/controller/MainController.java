package com.acheron.flowers.store.controller;


import com.acheron.flowers.mail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final EmailService emailVerifierService;

    @GetMapping("/api/v1/permit")
    public String data(){
//        emailVerifierService.verifyEmail("cocococococobil@gmail.com","sad");

        return "sa";
    }
    @PostMapping("/api/v1/permit")
    public String dat1a(){
//        emailVerifierService.verifyEmail("cocococococobil@gmail.com","sad");

        return "sa";
    }
}
