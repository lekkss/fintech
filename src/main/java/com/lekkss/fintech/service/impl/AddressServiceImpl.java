package com.lekkss.fintech.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lekkss.fintech.Repository.AddressRepository;
import com.lekkss.fintech.dto.AddressDto;
import com.lekkss.fintech.dto.AddressResponseDto;
import com.lekkss.fintech.entity.Address;
import com.lekkss.fintech.entity.User;
import com.lekkss.fintech.service.AddressService;
import com.lekkss.fintech.service.AuthService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AuthService authService;
    private final ModelMapper modelMapper;

    @Override
    public AddressResponseDto getAddressById(UUID addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        return modelMapper.map(address, AddressResponseDto.class);
    }

    @Override
    @Transactional
    public AddressResponseDto createAddress(AddressDto addressDto) {
        User user = authService.getAuthenticatedUser();

        if (user.getAddress() != null) {
            throw new RuntimeException("User already has an address");
        }

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setPostalCode(addressDto.getPostalCode());
        address.setUser(user);

        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto updateAddress(UUID addressId, AddressDto addressDto) {
        User user = authService.getAuthenticatedUser();
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to update this address");
        }

        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setPostalCode(addressDto.getPostalCode());

        Address updatedAddress = addressRepository.save(address);
        return modelMapper.map(updatedAddress, AddressResponseDto.class);
    }

    @Override
    @Transactional
    public void deleteAddress(UUID addressId) {
        User user = authService.getAuthenticatedUser();
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to delete this address");
        }

        // Set user's address to null
        user.setAddress(null);
        // Set address's user to null to break the bidirectional relationship
        address.setUser(null);

        // Delete the address
        addressRepository.delete(address);
    }
}
