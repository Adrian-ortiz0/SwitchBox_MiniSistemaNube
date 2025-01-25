package org.example.switchbox.domain.repository;

import jakarta.transaction.Transactional;
import org.example.switchbox.persistence.entity.Archivo;
import org.example.switchbox.persistence.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {

    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query("update Archivo a set a.nombre = :nombreArchivo where a.id = :id")
    void updateNombreArchivo(long id, String nombreArchivo);

    Optional<Archivo> findByNombre(String nombreArchivo);

    List<Archivo> findByUsuarioId(Long id);

    @Modifying
    @Transactional
    @Query("update Archivo a set a.favorito = true where a.id = :id")
    void markAsFavorite(Long id);

    @Modifying
    @Transactional
    @Query("update Archivo a set a.favorito = false where a.id = :id")
    void unMarkAsFavorite(Long id);

    @Query("select a from Archivo a where a.favorito = true and a.usuario.id = :id")
    List<Archivo> getAllFavoritosPorId(Long id);

}
