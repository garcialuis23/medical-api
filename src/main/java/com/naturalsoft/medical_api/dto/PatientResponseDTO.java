package com.naturalsoft.medical_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    // private String phoneNumber; // Podríamos ocultarlo si quisiéramos
    private String socialSecurityNumber; // Aquí irá enmascarado (****-1234)
    private LocalDate dateOfBirth;
}