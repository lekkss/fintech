package com.lekkss.fintech.service;

public interface EmailVerificationService {
    void sendVerificationEmail(String email);

    boolean verifyEmail(String email, String token);
}
