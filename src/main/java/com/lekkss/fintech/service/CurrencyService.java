package com.lekkss.fintech.service;

import java.util.List;
import java.util.UUID;

import com.lekkss.fintech.dto.CurrencyDto;

public interface CurrencyService {
    CurrencyDto createCurrency(CurrencyDto currencyDto);

    CurrencyDto getCurrencyById(UUID id);

    List<CurrencyDto> getAllCurrencies();
}
