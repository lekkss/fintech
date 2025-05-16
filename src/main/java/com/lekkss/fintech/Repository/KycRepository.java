package com.lekkss.fintech.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lekkss.fintech.entity.KycDetails;

public interface KycRepository extends JpaRepository<KycDetails, UUID> {
    Optional<KycDetails> findByUserId(UUID userId);
}
