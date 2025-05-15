package com.lekkss.fintech.ReqRes;

import com.lekkss.fintech.entity.Address;
import com.lekkss.fintech.entity.KycDetails;

import lombok.Data;

@Data
public class AuthRes {
    private String id;
    private String name;
    private String email;
    private boolean emailVerified;
    private boolean profileCompleted;
    private TokenInfo tokenInfo;
    private Address address;
    private KycDetails kycDetails;
    private boolean transactionPinActive;

}
