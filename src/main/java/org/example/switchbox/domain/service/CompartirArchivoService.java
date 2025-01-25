package org.example.switchbox.domain.service;

import org.example.switchbox.persistence.entity.CompartirArchivo;
import org.example.switchbox.persistence.entity.Usuario;

import java.util.List;

public interface CompartirArchivoService {
    CompartirArchivo compartirArchivo(Long archivoId, Long usuarioOrigenId, String emailDestinatario);

    List<CompartirArchivo> obtenerArchivosCompartidosConUsuario(Usuario usuario);
}
