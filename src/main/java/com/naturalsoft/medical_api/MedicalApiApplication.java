package com.naturalsoft.medical_api;

import com.naturalsoft.medical_api.model.UserEntity;
import com.naturalsoft.medical_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MedicalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalApiApplication.class, args);
	}

    // Bean para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Esto se ejecuta al arrancar la app
    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Si no hay usuarios, creamos el ADMIN por defecto
            if (userRepository.count() == 0) {
                UserEntity admin = new UserEntity();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // Encriptamos "admin123"
                admin.setRole("ADMIN");
                userRepository.save(admin);
                
                System.out.println("👑 USUARIO ADMIN CREADO: admin / admin123");
            }
        };
    }
}