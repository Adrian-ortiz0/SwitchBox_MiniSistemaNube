package org.example.switchbox.domain.service;

import org.example.switchbox.persistence.entity.Carpeta;
import org.example.switchbox.persistence.entity.Media;
import org.example.switchbox.persistence.entity.Usuario;

import java.util.List;

public interface CarpetaService {

    Carpeta getCarpetaById(long id);

    void deleteCarpetaByIdAndUsuario(Long userId, Long idCarpeta);

    Carpeta guardarCarpetaPorUsuario(Carpeta carpeta, Long userId);

    void updateNombreCarpeta(Long carpetaId, String nuevoNombre);

    List<Carpeta> findCarpetasByUsuario(long id);

    Carpeta crearCarpetaEnCarpeta(String nombre, Long usuarioId, Long carpetaPadreId);

    List<Carpeta> getCarpetasByCarpetaPadre(Long carpetaPadreId);

    List<Carpeta> getAllCarpetasByUsuario(Long usuarioId);
}
