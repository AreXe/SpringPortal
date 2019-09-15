package com.arexe.bgames.repository;

import com.arexe.bgames.entity.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Integer> {

    PasswordToken findPasswordTokenByPasswordToken(String passwordToken);
}