package com.lekkss.fintech.service;

import com.lekkss.fintech.ReqRes.AuthRes;
import com.lekkss.fintech.dto.AuthDto;
import com.lekkss.fintech.dto.RegisterDto;
import com.lekkss.fintech.entity.User;

public interface AuthService {
    AuthRes login(AuthDto authDto);

    AuthRes register(RegisterDto registerDto);

    User getAuthenticatedUser();
}
