package org.example.switchbox.web.controller;

import org.example.switchbox.domain.service.CompartirArchivoService;
import org.example.switchbox.persistence.entity.CompartirArchivo;
import org.example.switchbox.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compartir")
public class CompartirArchivoController {

    @Autowired
    private final CompartirArchivoService compartirArchivoService;

    @Autowired
    public CompartirArchivoController(CompartirArchivoService compartirArchivoService) {
        this.compartirArchivoService = compartirArchivoService;
    }

    @PostMapping("/archivo")
    public ResponseEntity<CompartirArchivo> compartirArchivo(
            @RequestParam Long archivoId,
            @RequestParam Long usuarioOrigenId,
            @RequestParam String emailDestino) {
        CompartirArchivo compartirArchivo = compartirArchivoService.compartirArchivo(archivoId, usuarioOrigenId, emailDestino);
        return ResponseEntity.ok(compartirArchivo);
    }

    @GetMapping("/archivos-compartidos")
    public ResponseEntity<List<CompartirArchivo>> obtenerArchivosCompartidosConUsuario(@RequestParam Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        List<CompartirArchivo> archivosCompartidos = compartirArchivoService.obtenerArchivosCompartidosConUsuario(usuario);
        return ResponseEntity.ok(archivosCompartidos);
    }
}
