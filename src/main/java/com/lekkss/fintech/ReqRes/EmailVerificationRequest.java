package com.lekkss.fintech.ReqRes;

import lombok.Data;

@Data
public class EmailVerificationRequest {
    private String email;
    private String token;
}
