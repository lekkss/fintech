package com.lekkss.fintech.service.impl;

import java.util.List;
import java.util.UUID;

import com.lekkss.fintech.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lekkss.fintech.Repository.DebitCardRepository;
import com.lekkss.fintech.dto.DebitCardDto;
import com.lekkss.fintech.entity.DebitCard;
import com.lekkss.fintech.entity.User;
import com.lekkss.fintech.service.DebitCardService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DebitCardServiceImpl implements DebitCardService {
    private final DebitCardRepository debitCardRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;

    @Override
    @Transactional
    public DebitCard addDebitCard(DebitCardDto debitCardDto) {
        User user = authService.getAuthenticatedUser();
        DebitCard debitCard = modelMapper.map(debitCardDto, DebitCard.class);
        // If this is the first card, set it as default
        if (user.getDebitCards().isEmpty()) {
            debitCard.setDefault(true);
        }
        debitCard.setUser(user);
        return debitCardRepository.save(debitCard);
    }

    @Override
    @Transactional
    public void setDefaultCard(UUID cardId) {
        // Remove default status from all cards
        User user = authService.getAuthenticatedUser();
        user.getDebitCards().forEach(card -> card.setDefault(false));

        // Set the selected card as default
        DebitCard selectedCard = debitCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Debit card not found"));
        selectedCard.setDefault(true);
        debitCardRepository.save(selectedCard);
    }

    @Override
    @Transactional
    public void removeDebitCard(UUID cardId) {
        User user = authService.getAuthenticatedUser();
        DebitCard card = debitCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Debit card not found"));

        if (card.isDefault() && user.getDebitCards().size() > 1) {
            throw new RuntimeException("Cannot remove default card. Please set another card as default first.");
        }

        debitCardRepository.delete(card);
    }

    @Override
    public List<DebitCard> getUserDebitCards() {
        User user = authService.getAuthenticatedUser();
        return debitCardRepository.findByUser(user);
    }

    @Override
    public DebitCard getDefaultCard() {
        User user = authService.getAuthenticatedUser();
        return debitCardRepository.findByUserAndIsDefaultTrue(user)
                .orElseThrow(() -> new RuntimeException("No default card found"));
    }
}
