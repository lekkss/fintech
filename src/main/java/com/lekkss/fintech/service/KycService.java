package com.lekkss.fintech.service;

import java.util.UUID;

import com.lekkss.fintech.dto.KycDetailsDto;
import com.lekkss.fintech.dto.KycResponseDto;
import com.lekkss.fintech.entity.enums.KycStatus;

public interface KycService {
    KycResponseDto submitKycDetails(KycDetailsDto kycDetailsDto);

    KycResponseDto getKycDetails(UUID userId);

    KycResponseDto updateKycStatus(UUID userId, KycStatus status);
}