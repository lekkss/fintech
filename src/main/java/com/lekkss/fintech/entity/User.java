package com.lekkss.fintech.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "transaction_pin")
    private String transactionPin;

    @Column(name = "transaction_pin_active", nullable = false)
    private boolean transactionPinActive = false;

    @Column(name = "is_email_verified")
    private boolean emailVerified;

    @Column(name = "is_profile_completed")
    private boolean profileCompleted;

    @Column(name = "verification_token")
    private String verificationToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Wallet> wallets = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private KycDetails kycDetails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<DebitCard> debitCards = new HashSet<>();

    public User() {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}