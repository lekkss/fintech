package com.lekkss.fintech.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lekkss.fintech.Repository.CurrencyRepository;
import com.lekkss.fintech.Repository.WalletRepository;
import com.lekkss.fintech.dto.WalletDto;
import com.lekkss.fintech.entity.Currency;
import com.lekkss.fintech.entity.User;
import com.lekkss.fintech.entity.Wallet;
import com.lekkss.fintech.service.AuthService;
import com.lekkss.fintech.service.WalletService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final CurrencyRepository currencyRepository;
    private final AuthService authService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public WalletDto createWallet(UUID userId) {
        User user = authService.getAuthenticatedUser();

        Currency currency = currencyRepository.findByCode("NGN")
                .orElseThrow(() -> new RuntimeException("Default currency not found"));

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setWalletNumber(UUID.randomUUID().toString());
        wallet.setName(currency.getCode() + " wallet");
        wallet.setCurrency(currency);
        wallet.setType("client");
        wallet.setActive(true);
        wallet.setMain(false);
        wallet.setAmount(BigDecimal.ZERO);

        return modelMapper.map(walletRepository.save(wallet), WalletDto.class);
    }

    @Override
    public WalletDto getWallet(UUID userId) {
        return modelMapper.map(
                walletRepository.findByUserId(userId)
                        .orElseThrow(() -> new RuntimeException("Wallet not found")),
                WalletDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WalletDto> getWalletsByUserId(UUID userId) {
        return walletRepository.findAll().stream()
                .map(wallet -> modelMapper.map(wallet, WalletDto.class))
                .collect(Collectors.toList());
    }
}
