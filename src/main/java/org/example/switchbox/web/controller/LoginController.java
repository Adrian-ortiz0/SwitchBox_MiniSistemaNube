package org.example.switchbox.web.controller;

import org.example.switchbox.domain.security.JWTAuthtenticationConfig;
import org.example.switchbox.domain.service.UsuarioServiceImpl;
import org.example.switchbox.persistence.entity.LoginUser;
import org.example.switchbox.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        if (usuarioService.verificarPorCorreo(email, password)) {
            String token = jwtAuthtenticationConfig.getJWTToken(email);

            Usuario usuario = usuarioService.findByEmail(email);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", usuario);

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
