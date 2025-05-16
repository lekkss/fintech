package com.lekkss.fintech.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lekkss.fintech.Repository.CurrencyRepository;
import com.lekkss.fintech.dto.CurrencyDto;
import com.lekkss.fintech.entity.Currency;
import com.lekkss.fintech.service.CurrencyService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        return currencyRepository.findAll().stream()
                .map(currency -> modelMapper.map(currency, CurrencyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CurrencyDto createCurrency(CurrencyDto currencyDto) {
        Currency currency = modelMapper.map(currencyDto, Currency.class);
        if (currencyRepository.existsByCode(currency.getCode())) {
            throw new RuntimeException("Currency already exists");
        }
        return modelMapper.map(currencyRepository.save(currency), CurrencyDto.class);
    }

    @Override
    public CurrencyDto getCurrencyById(UUID id) {
        return modelMapper.map(
                currencyRepository.findById(id).orElseThrow(() -> new RuntimeException("Currency not found")),
                CurrencyDto.class);
    }
}