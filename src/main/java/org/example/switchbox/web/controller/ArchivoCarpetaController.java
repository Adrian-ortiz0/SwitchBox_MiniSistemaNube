package org.example.switchbox.web.controller;

import org.example.switchbox.domain.repository.ArchivoCarpetaRepository;
import org.example.switchbox.domain.service.ArchivoCarpetaService;

import org.example.switchbox.domain.service.ArchivoService;
import org.example.switchbox.persistence.entity.Archivo;
import org.example.switchbox.persistence.entity.ArchivoCarpeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/archivos")
public class ArchivoCarpetaController {

    @Autowired
    private ArchivoCarpetaService archivoCarpetaService;

    @Autowired
    private ArchivoService archivoService;
    @Autowired
    private ArchivoCarpetaRepository archivoCarpetaRepository;

    @PutMapping("/mover/{archivoId}")
    public void moverArchivo(@PathVariable Long archivoId, @RequestBody Map<String, Long> requestBody) {
        Long carpetaId = requestBody.get("carpetaId");
        System.out.println("Archivo ID: " + archivoId);
        System.out.println("Carpeta ID: " + carpetaId);
        archivoCarpetaService.moverArchivo(archivoId, carpetaId);
    }

    @PostMapping("/uploadToFolder")
    public ResponseEntity<?> uploadArchivoToFolder(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("usuarioId") Long usuarioId,
                                                   @RequestParam("carpetaId") Long carpetaId) throws IOException {
        Archivo archivo = archivoService.subirArchivo(file, usuarioId);

        archivoCarpetaService.moverArchivo(archivo.getId(), carpetaId);

        return ResponseEntity.ok("Archivo subido y asignado a la carpeta con éxito");
    }

    @GetMapping("/carpeta/{carpetaId}")
    public List<ArchivoCarpeta> obtenerArchivosPorCarpeta(@PathVariable Long carpetaId) {
        return archivoCarpetaService.obtenerArchivosPorCarpeta(carpetaId);
    }

    @DeleteMapping("/carpeta/{carpetaId}")
    public void deleteArchivoPorCarpetaId(@PathVariable Long carpetaId, @RequestParam Long archivoId) {
        archivoCarpetaService.deleteArchivosPorId(carpetaId, archivoId);
    }

    @DeleteMapping("/carpeta/{carpetaId}/archivo/{archivoId}")
    public ResponseEntity<Void> eliminarArchivoDeCarpeta(@PathVariable Long carpetaId, @PathVariable Long archivoId) {
        archivoCarpetaService.eliminarArchivoDeCarpeta(carpetaId, archivoId);
        return ResponseEntity.noContent().build(); // Responde con un 204 No Content
    }


}
