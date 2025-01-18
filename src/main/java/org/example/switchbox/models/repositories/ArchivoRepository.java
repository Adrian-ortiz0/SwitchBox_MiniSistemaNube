package org.example.switchbox.models.repositories;

import jakarta.transaction.Transactional;
import org.example.switchbox.models.entities.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {

    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query("update Archivo a set a.nombre = :nombreArchivo where a.id = :id")
    void updateNombreArchivo(long id, String nombreArchivo);
    
    Optional<Archivo> findByNombre(String nombreArchivo);
}
