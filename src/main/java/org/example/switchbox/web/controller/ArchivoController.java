package org.example.switchbox.web.controller;

import org.example.switchbox.domain.service.ArchivoService;
import org.example.switchbox.persistence.entity.Archivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/archivos")
public class ArchivoController {

    private final ArchivoService archivoService;

    @Autowired
    public ArchivoController(ArchivoService archivoService) {
        this.archivoService = archivoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Archivo> obtenerArchivo(@PathVariable Long id) {
        try {
            Archivo archivo = archivoService.getArchivoById(id);
            return ResponseEntity.ok(archivo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/raiz/{usuarioId}")
    public ResponseEntity<List<Archivo>> obtenerArchivosRaiz(@PathVariable Long usuarioId) {
        List<Archivo> archivos = archivoService.getArchivosByUsuario(usuarioId);
        return ResponseEntity.ok(archivos);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("usuarioId") Long usuarioId) {
        try {
            archivoService.subirArchivo(file, usuarioId);
            return ResponseEntity.ok("Archivo subido con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir archivo");
        }
    }

    @PutMapping("/{archivoId}")
    public void updateNombreArchivo(@PathVariable Long archivoId, @RequestBody Archivo archivo) {
        archivoService.updateNombreArchivo(archivoId, archivo.getNombre());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarArchivo(@PathVariable Long id) {
        try {
            archivoService.deleteArchivo(id);
            return ResponseEntity.ok("Archivo eliminado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Archivo no encontrado");
        }
    }

    @PutMapping("/favorito/{id}")
    public void markAsFavorite(@PathVariable Long id) {
        archivoService.markAsFavorite(id);
    }

    @GetMapping("/archivosFavoritos/{usuarioId}")
    public List<Archivo> getAllFavorites(@PathVariable Long usuarioId) {
        return archivoService.getAllFavorites(usuarioId);
    }

    @PutMapping("/unmarkFavorito/{id}")
    public void unMarkAsFavorite(@PathVariable Long id) {
        archivoService.unMarkAsFavorite(id);
    }

}
