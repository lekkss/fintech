package com.lekkss.fintech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DebitCardDto {

    @NotBlank(message = "Card number is required")
    @Pattern(regexp = "^\\d{16}$", message = "Card number must be 16 digits")
    private String cardNumber;

    @NotBlank(message = "Card holder name is required")
    @Size(min = 3, max = 100, message = "Card holder name must be between 3 and 100 characters")
    private String cardHolderName;

    @NotBlank(message = "Expiry date is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/([0-9]{2})$", message = "Expiry date must be in MM/YY format")
    private String expiryDate;

    @NotBlank(message = "CVV is required")
    @Pattern(regexp = "^\\d{3,4}$", message = "CVV must be 3 or 4 digits")
    private String cvv;

    @NotBlank(message = "Bank name is required")
    private String bankName;
}
