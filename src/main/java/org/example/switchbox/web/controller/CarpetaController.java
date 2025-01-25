package org.example.switchbox.web.controller;

import org.example.switchbox.domain.service.CarpetaService;
import org.example.switchbox.persistence.entity.Archivo;
import org.example.switchbox.persistence.entity.Carpeta;
import org.example.switchbox.persistence.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carpetas")
public class CarpetaController {

    @Autowired
    private CarpetaService carpetaService;

    @PostMapping
    public ResponseEntity<?> createCarpeta(@RequestBody Carpeta carpeta, @RequestParam Long userId) {
        Carpeta nuevaCarpeta = carpetaService.guardarCarpetaPorUsuario(carpeta, userId);
        return ResponseEntity.ok(nuevaCarpeta);
    }

    @GetMapping("/usuario/{userId}")
    public List<Carpeta> getCarpetasByUsuario(@PathVariable Long userId) {
        return carpetaService.findCarpetasByUsuario(userId);
    }

    @DeleteMapping("/usuario/{userId}/{idCarpeta}")
    public void deleteCarpetaByUsuario(@PathVariable Long userId, @PathVariable Long idCarpeta) {
        carpetaService.deleteCarpetaByIdAndUsuario(userId, idCarpeta);
    }

    @PutMapping("/{carpetaId}")
    public void updateNombreCarpeta(@PathVariable Long carpetaId, @RequestBody Carpeta carpeta) {
        carpetaService.updateNombreCarpeta(carpetaId, carpeta.getNombre());
    }

    @PostMapping("/crearEnCarpeta")
    public ResponseEntity<?> crearCarpetaEnCarpeta(@RequestParam String nombre,
                                                   @RequestParam Long usuarioId,
                                                   @RequestParam Long carpetaPadreId) {
        System.out.println("Nombre: " + nombre);
        System.out.println("Usuario ID: " + usuarioId);
        System.out.println("Carpeta Padre ID: " + carpetaPadreId);

        Carpeta nuevaCarpeta = carpetaService.crearCarpetaEnCarpeta(nombre, usuarioId, carpetaPadreId);
        return ResponseEntity.ok(nuevaCarpeta);
    }

    @GetMapping("/carpetaPadre/{carpetaPadreId}")
    public ResponseEntity<List<Carpeta>> getCarpetasByCarpetaPadre(@PathVariable Long carpetaPadreId) {
        List<Carpeta> carpetas = carpetaService.getCarpetasByCarpetaPadre(carpetaPadreId);
        return ResponseEntity.ok(carpetas);
    }

    @GetMapping("/usuario/all-folders/{userId}")
    public List<Carpeta> getAllCarpetasByUsuario(@PathVariable Long userId) {
        return carpetaService.getAllCarpetasByUsuario(userId);
    }
}
