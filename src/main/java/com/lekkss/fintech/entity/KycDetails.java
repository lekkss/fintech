package com.lekkss.fintech.entity;

import com.lekkss.fintech.entity.enums.IdType;
import com.lekkss.fintech.entity.enums.KycStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kyc_details")
@Getter
@Setter
public class KycDetails extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "id_type", nullable = false)
    private IdType idType;

    @Column(name = "id_number", nullable = false)
    private String idNumber;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "id_issuing_country", nullable = false)
    private String idIssuingCountry;

    @Column(name = "id_expiry_date", nullable = false)
    private String idExpiryDate;

    @Column(name = "id_front_image_url")
    private String idFrontImageUrl;

    @Column(name = "id_back_image_url")
    private String idBackImageUrl;

    @Column(name = "selfie_image_url")
    private String selfieImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "kyc_status")
    private KycStatus kycStatus;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}