package com.softaura.auth_service.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role; // ADMIN, INTERN

    public void setPassword(String encode) {
    }

    public CharSequence getPassword() {
        return getPassword();
    }

    public String getEmail() {
        return getEmail();
    }
}