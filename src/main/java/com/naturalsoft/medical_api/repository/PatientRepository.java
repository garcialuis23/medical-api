package com.naturalsoft.medical_api.repository;

import com.naturalsoft.medical_api.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Spring crea el SQL automáticamente al leer el nombre del método:
    
    // SELECT * FROM patients WHERE email = ?
    Optional<Patient> findByEmail(String email);
    
    // SELECT count(*) > 0 FROM patients WHERE email = ?
    boolean existsByEmail(String email);
}