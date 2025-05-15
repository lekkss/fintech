package com.lekkss.fintech.ReqRes;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenInfo {
    private String token;
    private Date expirationDate;
}