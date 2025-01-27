package org.example.switchbox.web.controller;

import org.example.switchbox.domain.service.UsuarioServiceImpl;
import org.example.switchbox.persistence.entity.RegisterUser;
import org.example.switchbox.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUser registerUser) {
        try {
            if (usuarioService.emailExists(registerUser.getEmail())) {
                return ResponseEntity.badRequest().body("El correo ya está registrado.");
            }

            Usuario savedUser = usuarioService.registrar(registerUser);

            return ResponseEntity.ok("Usuario registrado exitosamente: " + savedUser.getEmail());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el usuario.");
        }
    }
}
