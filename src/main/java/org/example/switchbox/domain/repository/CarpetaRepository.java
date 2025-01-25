package org.example.switchbox.domain.repository;

import jakarta.transaction.Transactional;

import org.example.switchbox.persistence.entity.Archivo;
import org.example.switchbox.persistence.entity.Carpeta;
import org.example.switchbox.persistence.entity.Media;
import org.example.switchbox.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CarpetaRepository extends JpaRepository<Carpeta, Long> {

    @Query("select c from Carpeta c where c.usuario.id = :id and c.carpetaPadre is null")
    List<Carpeta> findCarpetasByUsuario(long id);

    @Query("select c from Carpeta c where c.usuario.id = :id")
    List<Carpeta> findAllCarpetasByUsuario(long id);

    @Modifying
    @Transactional
    @Query("UPDATE Carpeta c SET c.nombre = :nombreCarpeta WHERE c.id = :carpetaId")
    void updateNombreCarpeta(Long carpetaId, String nombreCarpeta);

    List<Carpeta> findByUsuarioId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Carpeta c WHERE c.id = :idCarpeta AND c.usuario.id = :userId")
    void deleteByIdAndUsuario_Id(@Param("idCarpeta") Long idCarpeta, @Param("userId") Long userId);

    @Query("select ac from ArchivoCarpeta ac where ac.carpeta.id = :id")
    List<Archivo> getArchivosByCarpetaId(Long id);

    @Query("select c from Carpeta c where c.carpetaPadre.id = :id")
    List<Carpeta> getCarpetasByCarpetaPadre(@Param("id") Long id);
}
