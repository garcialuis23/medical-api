package com.naturalsoft.medical_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action; // EJ: "CREATE_PATIENT", "DELETE_PATIENT"

    private String username; // Quién lo hizo (sacado del token JWT luego)

    private String details; // Ej: "Patient ID 5 created"

    private LocalDateTime timestamp;

    @PrePersist // Magia: Se ejecuta justo antes de guardar en BD automáticamente
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}