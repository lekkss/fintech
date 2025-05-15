package com.lekkss.fintech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lekkss.fintech.ReqRes.AuthRes;
import com.lekkss.fintech.ReqRes.ResponseHandler;
import com.lekkss.fintech.dto.AuthDto;
import com.lekkss.fintech.dto.RegisterDto;
import com.lekkss.fintech.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthDto authDto) {
        AuthRes authRes = authService.login(authDto);
        return ResponseHandler.generateResponse(
                "Login successful",
                HttpStatus.OK,
                authRes,
                false);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto) {
        AuthRes authRes = authService.register(registerDto);
        return ResponseHandler.generateResponse(
                "Registration successful",
                HttpStatus.CREATED,
                authRes,
                false);
    }
}
