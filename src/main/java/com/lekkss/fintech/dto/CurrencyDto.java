package com.lekkss.fintech.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CurrencyDto {
    private UUID id;
    private String code;
    private String symbol;
    private String name;
    private boolean active;
}