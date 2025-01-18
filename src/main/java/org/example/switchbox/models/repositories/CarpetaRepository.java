package org.example.switchbox.models.repositories;

import jakarta.transaction.Transactional;
import org.example.switchbox.models.entities.Carpeta;
import org.example.switchbox.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CarpetaRepository extends JpaRepository<Carpeta, Long> {

    @Query("select c from Carpeta c where c.usuario = :usuario")
    List<Carpeta> findCarpetasByUsuario(Usuario usuario);

    @Modifying
    @Transactional
    @Query("UPDATE Carpeta c SET c.nombre = :nombreCarpeta WHERE c.usuario = :usuario")
    void updateNombreCarpeta(Carpeta carpeta, String nombreCarpeta, Usuario usuario);

    List<Carpeta> findByUsuario(Usuario usuario);
}
