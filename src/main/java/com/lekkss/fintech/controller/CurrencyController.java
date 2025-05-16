package com.lekkss.fintech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lekkss.fintech.ReqRes.ResponseHandler;
import com.lekkss.fintech.dto.CurrencyDto;
import com.lekkss.fintech.service.CurrencyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/currencies")
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<Object> getAllCurrencies() {
        return ResponseHandler.generateResponse(
                "Currencies fetched successfully",
                HttpStatus.OK,
                currencyService.getAllCurrencies(),
                false);
    }

    @PostMapping
    public ResponseEntity<Object> createCurrency(@RequestBody CurrencyDto currencyDto) {
        return ResponseHandler.generateResponse(
                "Currency created successfully",
                HttpStatus.CREATED,
                currencyService.createCurrency(currencyDto),
                false);
    }
}