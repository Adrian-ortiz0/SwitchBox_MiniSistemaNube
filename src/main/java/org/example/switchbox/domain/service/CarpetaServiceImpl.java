package org.example.switchbox.domain.service;

import org.example.switchbox.domain.repository.CarpetaRepository;
import org.example.switchbox.domain.repository.UsuarioRepository;
import org.example.switchbox.persistence.entity.Carpeta;
import org.example.switchbox.persistence.entity.Media;
import org.example.switchbox.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarpetaServiceImpl implements CarpetaService {

    @Autowired
    private CarpetaRepository carpetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Carpeta getCarpetaById(long id) {
        return carpetaRepository.findById(id).orElseThrow(() -> new RuntimeException("Carpeta no encontrada"));
    }


    @Override
    public void deleteCarpetaByIdAndUsuario(Long userId, Long idCarpeta) {
        carpetaRepository.deleteByIdAndUsuario_Id(idCarpeta, userId);
    }


    @Override
    public Carpeta guardarCarpetaPorUsuario(Carpeta carpeta, Long userId) {
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        carpeta.setUsuario(usuario);
        Carpeta carpetaGuardada = carpetaRepository.save(carpeta);
        usuario.getCarpetas().add(carpetaGuardada);
        usuarioRepository.save(usuario);
        return carpetaGuardada;
    }

    @Override
    public Carpeta crearCarpetaEnCarpeta(String nombre, Long usuarioId, Long carpetaPadreId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + usuarioId + " no encontrado"));

        Carpeta carpetaPadre = carpetaRepository.findById(carpetaPadreId)
                .orElseThrow(() -> new RuntimeException("Carpeta padre con ID " + carpetaPadreId + " no encontrada"));

        Carpeta nuevaCarpeta = new Carpeta();
        nuevaCarpeta.setNombre(nombre);
        nuevaCarpeta.setUsuario(usuario);
        nuevaCarpeta.setCarpetaPadre(carpetaPadre);
        nuevaCarpeta.setTamaño(0);

        return carpetaRepository.save(nuevaCarpeta);
    }


    @Override
    public List<Carpeta> getCarpetasByCarpetaPadre(Long carpetaPadreId) {
        return carpetaRepository.getCarpetasByCarpetaPadre(carpetaPadreId);
    }

    @Override
    public List<Carpeta> getAllCarpetasByUsuario(Long usuarioId) {
        return carpetaRepository.findAllCarpetasByUsuario(usuarioId);
    }

    @Override
    public void updateNombreCarpeta(Long carpetaId, String nuevoNombre) {
        carpetaRepository.updateNombreCarpeta(carpetaId, nuevoNombre);
    }

    public List<Carpeta> findCarpetasByUsuario(long id) {
        return carpetaRepository.findCarpetasByUsuario(id);
    }
}
