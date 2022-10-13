package com.example.jsfcrud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {
    @GetMapping("/hello")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hello World");
    }
}
