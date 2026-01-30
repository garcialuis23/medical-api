package com.naturalsoft.medical_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientRequestDTO {
    
    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @Email(message = "El formato del email no es válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    private String phoneNumber;

    @NotBlank(message = "El número de la SS es obligatorio")
    private String socialSecurityNumber;

    private LocalDate dateOfBirth;
}