package com.lekkss.fintech.service.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lekkss.fintech.Repository.UserRepository;
import com.lekkss.fintech.ReqRes.AuthRes;
import com.lekkss.fintech.ReqRes.TokenInfo;
import com.lekkss.fintech.config.JwtService;
import com.lekkss.fintech.dto.AuthDto;
import com.lekkss.fintech.dto.RegisterDto;
import com.lekkss.fintech.dto.UserResponseDto;
import com.lekkss.fintech.entity.User;
import com.lekkss.fintech.service.AuthService;
import com.lekkss.fintech.service.EmailVerificationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final EmailVerificationService emailVerificationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    @Override
    public AuthRes login(AuthDto authDto) {
        System.out.println("Attempting login for email: " + authDto.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDto.getEmail(),
                        authDto.getPassword()));

        User user = userRepository.findByEmail(authDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);
        Date expirationDate = jwtService.extractExpiration(token);

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setToken(token);
        tokenInfo.setExpirationDate(expirationDate);

        // Map User to UserResponseDto
        UserResponseDto userResponse = modelMapper.map(user, UserResponseDto.class);

        // Map UserResponseDto to AuthRes
        AuthRes authRes = modelMapper.map(userResponse, AuthRes.class);
        authRes.setTokenInfo(tokenInfo);

        return authRes;
    }

    @Override
    @Transactional
    public AuthRes register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        // Map RegisterDto to User
        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(user);

        try {
            emailVerificationService.sendVerificationEmail(user.getEmail());
        } catch (Exception e) {
            throw new RuntimeException("Failed to send verification email. Registration cancelled.", e);
        }

        // Map User to UserResponseDto
        UserResponseDto userResponse = modelMapper.map(user, UserResponseDto.class);

        // Map UserResponseDto to AuthRes
        return modelMapper.map(userResponse, AuthRes.class);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
