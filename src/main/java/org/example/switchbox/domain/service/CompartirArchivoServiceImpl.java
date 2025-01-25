package org.example.switchbox.domain.service;

import jakarta.transaction.Transactional;
import org.example.switchbox.domain.repository.ArchivoRepository;
import org.example.switchbox.domain.repository.CompartirArchivoRepository;
import org.example.switchbox.domain.repository.UsuarioRepository;
import org.example.switchbox.persistence.entity.Archivo;
import org.example.switchbox.persistence.entity.CompartirArchivo;
import org.example.switchbox.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompartirArchivoServiceImpl implements CompartirArchivoService {


    @Autowired
    private final CompartirArchivoRepository compartirArchivoRepository;

    @Autowired
    private final ArchivoRepository archivoRepository;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CompartirArchivoServiceImpl(CompartirArchivoRepository compartirArchivoRepository,
                                       ArchivoRepository archivoRepository,
                                       UsuarioRepository usuarioRepository) {
        this.compartirArchivoRepository = compartirArchivoRepository;
        this.archivoRepository = archivoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public CompartirArchivo compartirArchivo(Long archivoId, Long usuarioOrigenId, String emailDestino) {
        Archivo archivo = archivoRepository.findById(archivoId)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
        Usuario usuarioOrigen = usuarioRepository.findById(usuarioOrigenId)
                .orElseThrow(() -> new RuntimeException("Usuario origen no encontrado"));
        Usuario usuarioDestino = usuarioRepository.findByEmail(emailDestino);

        CompartirArchivo compartirArchivo = new CompartirArchivo();
        compartirArchivo.setArchivo(archivo);
        compartirArchivo.setUsuarioOrigen(usuarioOrigen);
        compartirArchivo.setUsuarioDestino(usuarioDestino);

        return compartirArchivoRepository.save(compartirArchivo);
    }

    @Override
    @Transactional
    public List<CompartirArchivo> obtenerArchivosCompartidosConUsuario(Usuario usuario) {
        return compartirArchivoRepository.findArchivosCompartidosConmigo(usuario);
    }
}
