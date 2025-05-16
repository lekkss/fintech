package com.lekkss.fintech.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lekkss.fintech.Repository.KycRepository;
import com.lekkss.fintech.dto.KycDetailsDto;
import com.lekkss.fintech.dto.KycResponseDto;
import com.lekkss.fintech.entity.KycDetails;
import com.lekkss.fintech.entity.User;
import com.lekkss.fintech.entity.enums.KycStatus;
import com.lekkss.fintech.service.AuthService;
import com.lekkss.fintech.service.KycService;
import com.lekkss.fintech.service.WalletService;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KycServiceImpl implements KycService {

    private final KycRepository kycRepository;
    private final AuthService authService;
    private final WalletService walletService;
    private final ModelMapper modelMapper;

    @PostConstruct
    public void configureModelMapper() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
    }

    @Override
    @Transactional
    public KycResponseDto submitKycDetails(KycDetailsDto kycDetailsDto) {
        User user = authService.getAuthenticatedUser();

        if (user.getKycDetails() != null) {
            throw new RuntimeException("User already has KYC details");
        }

        KycDetails kycDetails = modelMapper.map(kycDetailsDto, KycDetails.class);
        kycDetails.setUser(user);
        kycDetails.setKycStatus(KycStatus.PENDING);

        KycDetails savedKyc = kycRepository.save(kycDetails);
        KycResponseDto response = modelMapper.map(savedKyc, KycResponseDto.class);
        response.setUserId(savedKyc.getUser().getId().toString());
        return response;
    }

    @Override
    public KycResponseDto getKycDetails(UUID userId) {
        KycDetails kycDetails = kycRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("KYC details not found"));
        KycResponseDto response = modelMapper.map(kycDetails, KycResponseDto.class);
        response.setUserId(kycDetails.getUser().getId().toString());
        return response;
    }

    @Override
    @Transactional
    public KycResponseDto updateKycStatus(UUID userId, KycStatus status) {
        KycDetails kycDetails = kycRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("KYC details not found"));

        kycDetails.setKycStatus(status);
        KycDetails updatedKyc = kycRepository.save(kycDetails);

        // If KYC is approved, create wallet and set profile as completed
        if (status == KycStatus.APPROVED) {
            User user = kycDetails.getUser();
            // Create wallet
            walletService.createWallet(user.getId());
            // Set profile as completed
            user.setProfileCompleted(true);
        }

        KycResponseDto response = modelMapper.map(updatedKyc, KycResponseDto.class);
        response.setUserId(updatedKyc.getUser().getId().toString());
        return response;
    }
}