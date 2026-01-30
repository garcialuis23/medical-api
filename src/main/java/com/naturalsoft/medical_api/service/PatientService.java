package com.naturalsoft.medical_api.service;

import com.naturalsoft.medical_api.dto.PatientRequestDTO;
import com.naturalsoft.medical_api.dto.PatientResponseDTO;
import com.naturalsoft.medical_api.model.AuditLog;
import com.naturalsoft.medical_api.model.Patient;
import com.naturalsoft.medical_api.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final AuditLogRepository auditLogRepository;

    // Inyección de dependencias por constructor (Best Practice)
    public PatientService(PatientRepository patientRepository, AuditLogRepository auditLogRepository) {
        this.patientRepository = patientRepository;
        this.auditLogRepository = auditLogRepository;
    }

    @Transactional // Si falla el log, se deshace el guardado del paciente (Atomicidad)
    public PatientResponseDTO createPatient(PatientRequestDTO request, String modifierUser) {
        // 1. Validar reglas de negocio (ej: email duplicado)
        if (patientRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // 2. Convertir DTO a Entidad
        Patient patient = new Patient();
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setEmail(request.getEmail());
        patient.setPhoneNumber(request.getPhoneNumber());
        patient.setSocialSecurityNumber(request.getSocialSecurityNumber());
        patient.setDateOfBirth(request.getDateOfBirth());

        // 3. Guardar en BD
        Patient savedPatient = patientRepository.save(patient);

        // 4. AUDITORÍA (Tu requisito TOP) ⭐
        AuditLog log = new AuditLog();
        log.setAction("CREATE_PATIENT");
        log.setUsername(modifierUser); // En el futuro vendrá del Token
        log.setDetails("Patient created with ID: " + savedPatient.getId());
        auditLogRepository.save(log);

        // 5. Devolver DTO enmascarado
        return mapToResponseDTO(savedPatient);
    }

    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Método helper para enmascarar datos (GDPR)
    private PatientResponseDTO mapToResponseDTO(Patient patient) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setEmail(patient.getEmail());
        dto.setDateOfBirth(patient.getDateOfBirth());
        
        // MASKING: Mostrar solo los últimos 4 dígitos
        String ssn = patient.getSocialSecurityNumber();
        if (ssn != null && ssn.length() > 4) {
            dto.setSocialSecurityNumber("****-" + ssn.substring(ssn.length() - 4));
        } else {
            dto.setSocialSecurityNumber("****");
        }
        
        return dto;
    }
}