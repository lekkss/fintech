package com.lekkss.fintech.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lekkss.fintech.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
    Optional<Currency> findByCode(String code);

    boolean existsByCode(String code);
}