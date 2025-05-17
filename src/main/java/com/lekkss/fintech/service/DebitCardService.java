package com.lekkss.fintech.service;

import java.util.List;
import java.util.UUID;

import com.lekkss.fintech.dto.DebitCardDto;
import com.lekkss.fintech.entity.DebitCard;
import com.lekkss.fintech.entity.User;

public interface DebitCardService {

    public DebitCard addDebitCard(DebitCardDto debitCardDto);

    public void setDefaultCard(UUID cardId);

    public void removeDebitCard(UUID cardId);

    public List<DebitCard> getUserDebitCards();

    public DebitCard getDefaultCard();
}
