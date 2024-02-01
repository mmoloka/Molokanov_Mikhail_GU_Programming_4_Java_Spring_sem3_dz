package org.example.sem3_dz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;
}
