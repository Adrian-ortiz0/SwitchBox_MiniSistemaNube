package org.example.switchbox.domain.service;

import jakarta.transaction.Transactional;
import org.example.switchbox.domain.repository.*;
import org.example.switchbox.domain.service.ArchivoService;
import org.example.switchbox.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArchivoServiceImpl implements ArchivoService {

    private final ArchivoRepository archivoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CompartirArchivoRepository compartirArchivoRepository;
    private final ArchivoCarpetaRepository archivoCarpetaRepository;
    private final CarpetaRepository carpetaRepository;


    @Autowired
    public ArchivoServiceImpl(ArchivoRepository archivoRepository,
                              UsuarioRepository usuarioRepository,
                              CompartirArchivoRepository compartirArchivoRepository,
                              ArchivoCarpetaRepository archivoCarpetaRepository, CarpetaRepository carpetaRepository) {
        this.archivoRepository = archivoRepository;
        this.usuarioRepository = usuarioRepository;
        this.compartirArchivoRepository = compartirArchivoRepository;
        this.archivoCarpetaRepository = archivoCarpetaRepository;
        this.carpetaRepository = carpetaRepository;
    }

    @Override
    public Archivo getArchivoById(Long id) {
        return archivoRepository.findById(id).orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
    }

    @Override
    public Archivo subirArchivo(MultipartFile file, Long usuarioId) throws IOException {
        String fileName = file.getOriginalFilename();

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Archivo archivo = new Archivo();
        archivo.setNombre(fileName);
        archivo.setTipo(file.getContentType());
        archivo.setFecha_subida(LocalDate.now());
        archivo.setTamaño(file.getSize());
        archivo.setUsuario(usuario);

        return archivoRepository.save(archivo);
    }


    @Override
    public void deleteArchivo(Long id) {
        archivoRepository.deleteById(id);
    }

    @Override
    public List<Archivo> getArchivosByUsuario(Long usuarioId) {
        return archivoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public void updateNombreArchivo(Long id, String nombreArchivo) {
        archivoRepository.updateNombreArchivo(id, nombreArchivo);
    }

    public void editarNombreArchivo(long id, String nombreArchivo) {
        archivoRepository.updateNombreArchivo(id, nombreArchivo);
    }


    public List<CompartirArchivo> getCompartirArchivos(Usuario usuario) {
        return compartirArchivoRepository.findArchivosCompartidosConmigo(usuario);
    }

    public Archivo obtenerArchivoPorNombre(String nombreArchivo) {
        Optional<Archivo> archivoOpt = archivoRepository.findByNombre(nombreArchivo);
        return archivoOpt.orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
    }

    public ArchivoCarpeta save(ArchivoCarpeta archivoCarpeta) {
        return archivoCarpetaRepository.save(archivoCarpeta);
    }

    public void markAsFavorite(Long id) {
        archivoRepository.markAsFavorite(id);
    }

    public void unMarkAsFavorite(Long id) {
        archivoRepository.unMarkAsFavorite(id);
    }

    public List<Archivo> getAllFavorites(Long id) {
        return archivoRepository.getAllFavoritosPorId(id);
    }

}
