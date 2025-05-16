package com.lekkss.fintech.dto;

import com.lekkss.fintech.entity.enums.IdType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class KycDetailsDto {
    @NotNull(message = "ID type is required")
    private IdType idType;

    @NotBlank(message = "ID number is required")
    private String idNumber;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @NotBlank(message = "ID issuing country is required")
    private String idIssuingCountry;

    @NotBlank(message = "ID expiry date is required")
    private String idExpiryDate;

    @NotBlank(message = "ID front image URL is required")
    private String idFrontImageUrl;

    @NotBlank(message = "ID back image URL is required")
    private String idBackImageUrl;

    @NotBlank(message = "Selfie image URL is required")
    private String selfieImageUrl;
}