package com.ecommerce.checkout_orchestrator.controller;

import com.ecommerce.checkout_orchestrator.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {

        if ("administrador".equals(username) && "admin123".equals(password)) {

            String token = jwtUtil.generarToken(username);
            return ResponseEntity.ok(token);

        }

        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}