package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.ShopDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.service.ShopService;

@RestController
@RequestMapping("/v1")
public class InitialController {
    @Autowired
    public InitialController(){
    }

    @GetMapping("/test")
    public String helloGradle() {
        return "Hello Gradle!";
    }
}
