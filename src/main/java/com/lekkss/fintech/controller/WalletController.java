package com.lekkss.fintech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lekkss.fintech.ReqRes.ResponseHandler;
import com.lekkss.fintech.service.AuthService;
import com.lekkss.fintech.service.WalletService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/wallets")
@AllArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<Object> getWallet() {
        return ResponseHandler.generateResponse(
                "Wallet fetched successfully",
                HttpStatus.OK,
                walletService.getWalletsByUserId(authService.getAuthenticatedUser().getId()),
                false);
    }
}