package com.arexe.portal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role implements Serializable {

    private static final long serialVersionUID = 4464105491421074675L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    @NotNull
    private String role;

}
