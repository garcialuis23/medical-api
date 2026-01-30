package com.naturalsoft.medical_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            // 1. Desactivamos CSRF para permitir POST desde Postman
            .csrf(csrf -> csrf.disable()) 
            
            // 2. Decimos que CUALQUIER petición requiere estar autenticado
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            
            // 3. Activamos la autenticación básica (usuario/contraseña que ya usas)
            .httpBasic(Customizer.withDefaults())
            
            .build();
    }
}