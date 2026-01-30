package com.naturalsoft.medical_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users") // La tabla se llamará 'users' en Postgres
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // Aquí guardaremos el HASH, nunca texto plano

    private String role; // "ADMIN" o "USER"
}