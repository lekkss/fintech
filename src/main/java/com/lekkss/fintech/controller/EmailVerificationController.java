package com.lekkss.fintech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lekkss.fintech.ReqRes.EmailVerificationRequest;
import com.lekkss.fintech.ReqRes.ResponseHandler;
import com.lekkss.fintech.service.EmailVerificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;

    @PostMapping("/send-verification-email")
    public ResponseEntity<Object> sendVerificationEmail(@RequestParam String email) {
        emailVerificationService.sendVerificationEmail(email);
        return ResponseHandler.generateResponse(
                "Verification email sent",
                HttpStatus.OK,
                null,
                false);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<Object> verifyEmail(
            @RequestBody EmailVerificationRequest request) {
        emailVerificationService.verifyEmail(request.getEmail(), request.getToken());
        return ResponseHandler.generateResponse(
                "Email verified",
                HttpStatus.OK, null, false);
    }
}
