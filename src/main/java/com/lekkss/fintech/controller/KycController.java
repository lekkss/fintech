package com.lekkss.fintech.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lekkss.fintech.ReqRes.ResponseHandler;
import com.lekkss.fintech.dto.KycDetailsDto;
import com.lekkss.fintech.dto.KycResponseDto;
import com.lekkss.fintech.entity.enums.KycStatus;
import com.lekkss.fintech.service.KycService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kyc")
@RequiredArgsConstructor
public class KycController {

    private final KycService kycService;

    @PostMapping
    public ResponseEntity<Object> submitKycDetails(@RequestBody KycDetailsDto kycDetailsDto) {
        KycResponseDto response = kycService.submitKycDetails(kycDetailsDto);
        return ResponseHandler.generateResponse(
                "KYC details submitted successfully",
                HttpStatus.CREATED,
                response,
                false);
    }

    @GetMapping
    public ResponseEntity<Object> getKycDetails(@RequestParam UUID userId) {
        KycResponseDto response = kycService.getKycDetails(userId);
        return ResponseHandler.generateResponse(
                "KYC details retrieved successfully",
                HttpStatus.OK,
                response,
                false);
    }

    @PutMapping("/status")
    public ResponseEntity<Object> updateKycStatus(
            @RequestParam UUID userId,
            @RequestParam KycStatus status) {
        KycResponseDto response = kycService.updateKycStatus(userId, status);
        return ResponseHandler.generateResponse(
                "KYC status updated successfully",
                HttpStatus.OK,
                response,
                false);
    }
}