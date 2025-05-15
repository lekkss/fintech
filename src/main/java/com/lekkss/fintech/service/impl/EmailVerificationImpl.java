package com.lekkss.fintech.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lekkss.fintech.Repository.UserRepository;
import com.lekkss.fintech.config.JwtService;
import com.lekkss.fintech.dto.EmailDetails;
import com.lekkss.fintech.entity.User;
import com.lekkss.fintech.service.EmailVerificationService;

@Service
public class EmailVerificationImpl implements EmailVerificationService {

    private final JavaMailSender emailSender;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${app.verification.url}")
    private String verificationBaseUrl;

    @Autowired
    public EmailVerificationImpl(JavaMailSender emailSender, UserRepository userRepository, JwtService jwtService) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void sendVerificationEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        String token = jwtService.generateToken(user.get());
        user.get().setVerificationToken(token);
        userRepository.save(user.get());

        String subject = "Verify your email";
        String verificationUrl = verificationBaseUrl + "?email=" + email + "&token=" + token;
        String text = "Click the link to verify your email: " + verificationUrl;

        System.out.println("verificationUrl: " + verificationUrl);

        EmailDetails details = new EmailDetails();
        details.setRecipient(email);
        details.setSubject(subject);
        details.setMsgBody(text);

        sendSimpleMail(details);
    }

    @Override
    public boolean verifyEmail(String email, String token) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        if (user.get().isEmailVerified()) {
            throw new RuntimeException("Email already verified");
        }
        if (!token.equals(user.get().getVerificationToken())) {
            throw new RuntimeException("Invalid or expired token");
        }
        user.get().setEmailVerified(true);
        user.get().setVerificationToken(null);
        userRepository.save(user.get());
        return true;
    }

    public String sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            emailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (MailException e) {
            return "Error while Sending Mail: " + e.getMessage();
        }
    }
}
