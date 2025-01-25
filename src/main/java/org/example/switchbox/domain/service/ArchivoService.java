package org.example.switchbox.domain.service;

import org.example.switchbox.persistence.entity.Archivo;
import org.example.switchbox.persistence.entity.Media;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArchivoService {

    Archivo getArchivoById(Long id);

    Archivo subirArchivo(MultipartFile file, Long usuarioId) throws IOException;

    void deleteArchivo(Long id);

    List<Archivo> getArchivosByUsuario(Long usuarioId);

    void updateNombreArchivo(Long id, String nombreArchivo);

    void markAsFavorite(Long id);

    void unMarkAsFavorite(Long id);

    List<Archivo> getAllFavorites(Long id);
}
