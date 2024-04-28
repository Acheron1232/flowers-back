package com.acheron.flowers.store.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/api/v1/permit")
    public String data(){
//        System.out.println(principal)

        return "sa";
    }
}
