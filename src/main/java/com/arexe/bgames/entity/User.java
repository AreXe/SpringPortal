package com.arexe.bgames.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "appuser")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6986527833296330728L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "login")
    @NotNull
    @Size(min = 3, max = 30, message = "3 to 30 characters")
    private String login;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "first_name")
    @NotNull
    @Size(min = 1, max = 50, message = "1 to 50 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(min = 1, max = 50, message = "1 to 50 characters")
    private String lastName;

    @Column(name = "active")
    @NotNull
    private int active;

    @Column(name = "activation_token")
    private String activationToken;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Transient
    private int roleNumber;

    @Transient
    private String newPassword;

    public User() {
        //for JPA
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
