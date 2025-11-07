package com.softaura.auth_service.controller;

import com.softaura.auth_service.dto.LoginRequest;
import com.softaura.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // Optional: dev-only endpoint to create admin (not exposed in prod)
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.createAdminIfNotExists(request.getUsername(), request.getPassword()));
    }
}
