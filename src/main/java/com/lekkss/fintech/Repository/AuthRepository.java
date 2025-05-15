package com.lekkss.fintech.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lekkss.fintech.entity.User;

public interface AuthRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
