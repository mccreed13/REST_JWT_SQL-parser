package com.example.secured_rest.controllers;

import com.example.secured_rest.dtos.JwtRequest;
import com.example.secured_rest.dtos.RegistationUserDto;
import com.example.secured_rest.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@RequestBody RegistationUserDto userDto) {
        return authService.add(userDto);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> auth(@RequestBody JwtRequest authRequest) {
        System.out.println("Sss");
        return authService.auth(authRequest);
    }
}
