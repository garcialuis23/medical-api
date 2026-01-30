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
        // "admin" es temporal hasta que tengamos el JWT en la Fase 3
        PatientResponseDTO newPatient = patientService.createPatient(request, "admin_temp");
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
}