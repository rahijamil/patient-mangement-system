package com.pm.auth_service.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pm.auth_service.dto.LoginRequestDTO;
import com.pm.auth_service.dto.LoginResponseDTO;
import com.pm.auth_service.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Generate token on user login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<String> tokeOptional = authService.authenticate(loginRequestDTO);

        if (tokeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Unauthorized
        }

        String token = tokeOptional.get();
        LoginResponseDTO response = new LoginResponseDTO(token);
        return ResponseEntity.ok(response); // OK with token
    }

    @Operation(summary = "Validate Token")
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(
            @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Unauthorized
        }

        return authService.validateToken(authHeader.substring(7)) ? ResponseEntity.ok().build() 
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Unauthorized
    }
}
