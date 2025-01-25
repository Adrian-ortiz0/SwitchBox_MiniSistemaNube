package org.example.switchbox.domain.service;

import org.example.switchbox.persistence.entity.ArchivoCarpeta;

import java.util.List;

public interface ArchivoCarpetaService {
    void moverArchivo(Long archivoId, Long carpetaId);

    List<ArchivoCarpeta> obtenerArchivosPorCarpeta(Long carpetaId);

    void deleteArchivosPorId(Long carpetaId, Long archivoId);

    void eliminarArchivoDeCarpeta(Long carpetaId, Long archivoId);
}
