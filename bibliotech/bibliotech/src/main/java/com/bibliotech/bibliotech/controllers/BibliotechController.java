package com.bibliotech.bibliotech.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/root")
public class BibliotechController {

    @GetMapping("")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("Bibliotech");
    }
}
