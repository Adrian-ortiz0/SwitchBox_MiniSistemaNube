package org.example.switchbox.controller;

import org.example.switchbox.models.entities.Archivo;
import org.example.switchbox.models.entities.CompartirArchivo;
import org.example.switchbox.models.entities.Usuario;
import org.example.switchbox.models.repositories.ArchivoRepository;
import org.example.switchbox.models.repositories.CompartirArchivoRepository;
import org.example.switchbox.models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompartirArchivoRepository compartirArchivoRepository;

    public List<Archivo> findAll() {
        return archivoRepository.findAll();
    }

    public Archivo save(Archivo archivo) {
        return archivoRepository.save(archivo);
    }

    public List<Archivo> getArchivos(long id) {
        return usuarioRepository.getArchivosByUsuario(id);
    }

    public void deleteArchivoById(long id) {
        archivoRepository.deleteById(id);
        System.out.println("Archivo eliminado con exito!");
        archivoRepository.flush();
    }

    public void editarNombreArchivo(long id, String nombreArchivo) {
        archivoRepository.updateNombreArchivo(id, nombreArchivo);
        System.out.println("Archivo editado con exito!");
    }

    public void compartirArchivo(CompartirArchivo compartirArchivo) {
        compartirArchivoRepository.save(compartirArchivo);
        System.out.println("Archivo compartido con exito!");
        compartirArchivoRepository.flush();
    }

    public List<CompartirArchivo> getCompartirArchivos(Usuario usuario) {
        return compartirArchivoRepository.findArchivosCompartidosConmigo(usuario);
    }

    public Archivo obtenerArchivoPorNombre(String nombreArchivo) {
        Optional<Archivo> archivoOpt = archivoRepository.findByNombre(nombreArchivo);
        return archivoOpt.orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
    }

}
