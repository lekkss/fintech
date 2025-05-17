package com.lekkss.fintech.controller;


import com.lekkss.fintech.ReqRes.ResponseHandler;
import com.lekkss.fintech.dto.DebitCardDto;
import com.lekkss.fintech.entity.DebitCard;
import com.lekkss.fintech.service.DebitCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class DebitCardController {
    private final DebitCardService debitCardService;

    @GetMapping
    public ResponseEntity<Object> getCard() {
        return ResponseHandler.generateResponse("Card fetched successfully", HttpStatus.OK, debitCardService.getUserDebitCards(), false);
    }

    @PostMapping
    public ResponseEntity<Object> createCard(@RequestBody DebitCardDto debitCard) {
        return ResponseHandler.generateResponse("Card created successful", HttpStatus.CREATED, debitCardService.addDebitCard(debitCard), true);
    }
}
