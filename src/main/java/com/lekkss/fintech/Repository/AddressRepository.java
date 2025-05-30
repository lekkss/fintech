package com.lekkss.fintech.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lekkss.fintech.entity.Address;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    Optional<Address> findByUserId(UUID userId);

}
