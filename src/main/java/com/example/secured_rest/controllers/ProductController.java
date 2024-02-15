package com.example.secured_rest.controllers;

import com.example.secured_rest.dtos.Table;
import com.example.secured_rest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@RequestBody Table table) {
        return ResponseEntity.ok(table);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok("fsfs");
    }
}
