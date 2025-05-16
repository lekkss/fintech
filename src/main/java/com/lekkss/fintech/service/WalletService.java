package com.lekkss.fintech.service;

import java.util.List;
import java.util.UUID;

import com.lekkss.fintech.dto.WalletDto;

public interface WalletService {
    WalletDto createWallet(UUID userId);

    WalletDto getWallet(UUID userId);

    List<WalletDto> getWalletsByUserId(UUID userId);

}
