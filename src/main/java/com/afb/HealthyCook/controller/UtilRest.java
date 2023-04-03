package com.afb.HealthyCook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/util")
public class UtilRest {
    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hola");
    }
}
