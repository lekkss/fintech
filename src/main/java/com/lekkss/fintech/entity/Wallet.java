package com.lekkss.fintech.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wallets")
@Getter
@Setter
public class Wallet extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String walletNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private boolean main = false;

    @Column(nullable = false)
    private String type = "client";

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "user-wallet")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Wallet() {
        super();
        this.amount = BigDecimal.ZERO;
    }
}