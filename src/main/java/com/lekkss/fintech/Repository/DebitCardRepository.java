package com.lekkss.fintech.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lekkss.fintech.entity.DebitCard;
import com.lekkss.fintech.entity.User;

@Repository
public interface DebitCardRepository extends JpaRepository<DebitCard, UUID> {
    List<DebitCard> findByUser(User user);

    Optional<DebitCard> findByUserAndIsDefaultTrue(User user);

    Optional<DebitCard> findByCardNumber(String cardNumber);
}