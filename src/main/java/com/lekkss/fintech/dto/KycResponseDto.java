package com.lekkss.fintech.dto;

import java.util.UUID;

import com.lekkss.fintech.entity.enums.IdType;
import com.lekkss.fintech.entity.enums.KycStatus;

import lombok.Data;

@Data
public class KycResponseDto {
    private UUID id;
    private IdType idType;
    private String idNumber;
    private String dateOfBirth;
    private String idIssuingCountry;
    private String idExpiryDate;
    private String idFrontImageUrl;
    private String idBackImageUrl;
    private String selfieImageUrl;
    private KycStatus kycStatus;
    private String userId;
}