package com.naturalsoft.medical_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity // 1. Dice a JPA: "Esto es una tabla en la BD"
@Table(name = "patients") // 2. Nombre real de la tabla en Postgres
@Data // 3. Lombok: Genera Getters, Setters, ToString, etc. automático
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental (1, 2, 3...)
    private Long id;

    @Column(nullable = false) // No puede ser null en BD
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true) // El email no se puede repetir
    private String email;

    // DATOS SENSIBLES (GDPR) - Esto justificará el enmascarado luego
    private String phoneNumber;
    
    @Column(name = "social_security_number")
    private String socialSecurityNumber; // Este es el dato crítico a proteger

    private LocalDate dateOfBirth;
}