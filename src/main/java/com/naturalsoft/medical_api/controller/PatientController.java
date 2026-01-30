package com.naturalsoft.medical_api.controller;

import com.naturalsoft.medical_api.dto.PatientRequestDTO;
import com.naturalsoft.medical_api.dto.PatientResponseDTO;
import com.naturalsoft.medical_api.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO request) {
        // 1. Sacamos el usuario autenticado del contexto de seguridad (el que viene en el Token)
        String currentUsername = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getName();

        // 2. Se lo pasamos al servicio para que el AuditLog sea real
        PatientResponseDTO newPatient = patientService.createPatient(request, currentUsername);
        
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
}