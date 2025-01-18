package org.example.switchbox.controller;

import org.example.switchbox.models.entities.Carpeta;
import org.example.switchbox.models.entities.Usuario;
import org.example.switchbox.models.repositories.CarpetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarpetaService {

    @Autowired
    private CarpetaRepository carpetaRepository;

    public void deleteCarpeta(Carpeta carpeta) {
        carpetaRepository.delete(carpeta);
    }

    public Carpeta save(Carpeta carpeta) {
        return carpetaRepository.save(carpeta);
    }

    public List<Carpeta> findCarpetasByUsuario(Usuario usuario) {
        return carpetaRepository.findCarpetasByUsuario(usuario);
    }

    public void updateNombreCarpeta(Carpeta carpeta, String nombre, Usuario usuario) {
        carpetaRepository.updateNombreCarpeta(carpeta, nombre, usuario);
        System.out.println("Carpeta editada con exito!");
    }
}
