package com.softaura.auth_service.service;

import com.softaura.auth_service.dto.LoginRequest;
import com.softaura.auth_service.entity.Admin;
import com.softaura.auth_service.repository.AdminRepository;
import com.softaura.auth_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository repo;
    private final JwtUtil jwt;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Map<String, Object> login(LoginRequest req) {
        Admin admin = repo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(req.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwt.generateToken(admin.getUsername());
        return Map.of(
                "token", token,
                "username", admin.getUsername(),
                "role", admin.getRole()
        );
    }

    // Optional: method to create admin in dev environment
    public Admin createAdminIfNotExists(String username, String rawPassword) {
        return repo.findByUsername(username).orElseGet(() -> {
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(passwordEncoder.encode(rawPassword));
            admin.setRole("ADMIN");
            return repo.save(admin);
        });
    }
}
