package com.naturalsoft.medical_api.service;

import com.naturalsoft.medical_api.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    // No hace falta escribir nada más. 
    // Al extender JpaRepository, Spring ya te regala el método .save() que usas en tu servicio.
}