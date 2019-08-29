package com.arexe.bgames.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_token")
@Data
public class PasswordToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_token_id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password_token")
    private String passwordToken;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    public PasswordToken() {
        //for JPA
    }
}
