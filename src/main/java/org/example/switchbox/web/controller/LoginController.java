package org.example.switchbox.web.controller;

import org.example.switchbox.domain.security.JWTAuthtenticationConfig;
import org.example.switchbox.domain.service.UsuarioServiceImpl;
import org.example.switchbox.persistence.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

            LoginUser user = new LoginUser(email, password, token);
            return ResponseEntity.ok(user);
        }
        return null;

    }
}
