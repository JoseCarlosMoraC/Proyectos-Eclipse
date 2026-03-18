package com.spring.controllers;

import com.spring.auth.AuthResponse;
import com.spring.auth.LoginRequest;
import com.spring.auth.RegisterRequest;
import com.spring.exceptions.CoachXBadRequestException;
import com.spring.models.Usuario;
import com.spring.repositories.UsuarioRepository;
import com.spring.security.JwtUtil;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coachx/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registro(@Valid @RequestBody RegisterRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new CoachXBadRequestException(
                    "El username '" + request.getUsername() + "' ya está en uso");
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuarioRepository.save(usuario);

        String token = jwtUtil.generarToken(usuario.getUsername());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new AuthResponse(token, usuario.getUsername(),
                        "Usuario registrado correctamente"));
    }

  
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CoachXBadRequestException(
                        "Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new CoachXBadRequestException("Credenciales inválidas");
        }

        String token = jwtUtil.generarToken(usuario.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, usuario.getUsername(),
                "Login correcto"));
    }
}
