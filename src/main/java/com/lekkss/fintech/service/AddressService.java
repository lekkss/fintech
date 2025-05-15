package com.lekkss.fintech.service;

import java.util.UUID;

import com.lekkss.fintech.dto.AddressDto;
import com.lekkss.fintech.dto.AddressResponseDto;

public interface AddressService {

    AddressResponseDto getAddressById(UUID addressId);

    AddressResponseDto createAddress(AddressDto addressDto);

    AddressResponseDto updateAddress(UUID addressId, AddressDto addressDto);

    void deleteAddress(UUID addressId);
}