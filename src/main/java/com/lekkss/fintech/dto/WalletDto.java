package com.lekkss.fintech.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class WalletDto {
    private String walletNumber;
    private String name;
    private BigDecimal amount;
    private CurrencyDto currency;
    private boolean active;
    private boolean main;
    private String type;
    private UUID userId;
}
