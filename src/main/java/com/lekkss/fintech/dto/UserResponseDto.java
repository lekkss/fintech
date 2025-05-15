package com.lekkss.fintech.dto;

import com.lekkss.fintech.entity.Address;
import com.lekkss.fintech.entity.KycDetails;

import lombok.Data;

@Data
public class UserResponseDto {
    private String id;
    private String name;
    private String email;
    private boolean emailVerified;
    private boolean profileCompleted;
    private boolean transactionPinActive;
    private Address address;
    private KycDetails kycDetails;
}