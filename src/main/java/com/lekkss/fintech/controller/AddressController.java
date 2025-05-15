package com.lekkss.fintech.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lekkss.fintech.ReqRes.ResponseHandler;
import com.lekkss.fintech.dto.AddressDto;
import com.lekkss.fintech.dto.AddressResponseDto;
import com.lekkss.fintech.service.AddressService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/addresses")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Object> createAddress(@RequestBody AddressDto addressDto) {
        AddressResponseDto createdAddress = addressService.createAddress(addressDto);
        return ResponseHandler.generateResponse(
                "Address created successfully",
                HttpStatus.CREATED,
                createdAddress,
                false);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Object> getAddressById(@PathVariable String addressId) {
        try {
            UUID uuid = UUID.fromString(addressId);
            AddressResponseDto address = addressService.getAddressById(uuid);
            return ResponseHandler.generateResponse(
                    "Address retrieved successfully",
                    HttpStatus.OK,
                    address,
                    false);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid address ID format");
        }
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Object> updateAddress(@PathVariable String addressId, @RequestBody AddressDto addressDto) {
        try {
            UUID uuid = UUID.fromString(addressId);
            AddressResponseDto updatedAddress = addressService.updateAddress(uuid, addressDto);
            return ResponseHandler.generateResponse(
                    "Address updated successfully",
                    HttpStatus.OK,
                    updatedAddress,
                    false);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid address ID format");
        }
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Object> deleteAddress(@PathVariable String addressId) {
        try {
            UUID uuid = UUID.fromString(addressId);
            addressService.deleteAddress(uuid);
            return ResponseHandler.generateResponse("Address deleted successfully", HttpStatus.OK, null, false);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid address ID format");
        }
    }
}
