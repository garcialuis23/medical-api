package com.naturalsoft.medical_api.controller;

import com.naturalsoft.medical_api.dto.AuthResponseDTO;
import com.naturalsoft.medical_api.dto.LoginRequestDTO;
import com.naturalsoft.medical_api.util.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        // 1. Esto autentica contra la base de datos (si falla, lanza excepción)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // 2. Si llegamos aquí, usuario y pass son correctos. Generamos Token.
        if (authentication.isAuthenticated()) {
            String token = jwtUtils.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponseDTO(token));
        } else {
            throw new RuntimeException("Authentication invalid");
        }
    }
}