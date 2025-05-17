package com.lekkss.fintech.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "debit_cards")
@Getter
@Setter
public class DebitCard extends BaseEntity {

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "card_holder_name", nullable = false)
    private String cardHolderName;

    @Column(name = "expiry_date", nullable = false)
    private String expiryDate;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "user-debitCard")
    private User user;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}