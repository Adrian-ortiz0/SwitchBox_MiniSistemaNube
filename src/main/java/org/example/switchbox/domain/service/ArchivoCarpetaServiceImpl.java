package org.example.switchbox.domain.service;

import jakarta.transaction.Transactional;
import org.example.switchbox.domain.repository.ArchivoCarpetaRepository;
import org.example.switchbox.domain.repository.ArchivoRepository;
import org.example.switchbox.domain.repository.CarpetaRepository;
import org.example.switchbox.persistence.entity.Archivo;
import org.example.switchbox.persistence.entity.ArchivoCarpeta;
import org.example.switchbox.persistence.entity.Carpeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivoCarpetaServiceImpl implements ArchivoCarpetaService {

    @Autowired
    private ArchivoCarpetaRepository archivoCarpetaRepository;
    @Autowired
    private ArchivoRepository archivoRepository;
    @Autowired
    private CarpetaRepository carpetaRepository;

    @Override
    @Transactional
    public void moverArchivo(Long archivoId, Long carpetaId) {
        try {
            Archivo archivo = archivoRepository.findById(archivoId)
                    .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
            Carpeta carpeta = carpetaRepository.findById(carpetaId)
                    .orElseThrow(() -> new RuntimeException("Carpeta no encontrada"));
////
////            // Elimina la relación actual del archivo, si existe
//            archivoCarpetaRepository.deleteByArchivoId(archivoId);

            // Crea una nueva relación entre el archivo y la carpeta
            ArchivoCarpeta nuevaRelacion = new ArchivoCarpeta(archivo, carpeta);
            archivoCarpetaRepository.save(nuevaRelacion);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error moviendo archivo: " + e.getMessage());
        }
    }

    @Override
    public List<ArchivoCarpeta> obtenerArchivosPorCarpeta(Long carpetaId) {
        return archivoCarpetaRepository.findByCarpetaId(carpetaId);
    }

    @Override
    public void deleteArchivosPorId(Long carpetaId, Long archivoId) {
        archivoCarpetaRepository.deleteByArchivoId(carpetaId, archivoId);
    }

    @Override
    public void eliminarArchivoDeCarpeta(Long carpetaId, Long archivoId) {
        archivoCarpetaRepository.deleteByArchivoId(carpetaId, archivoId);
    }


}
