package org.example.switchbox.domain.repository;

import jakarta.transaction.Transactional;
import org.example.switchbox.persistence.entity.ArchivoCarpeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivoCarpetaRepository extends JpaRepository<ArchivoCarpeta, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ArchivoCarpeta ac WHERE ac.carpeta.id = :carpetaId AND ac.archivo.id = :archivoId")
    void deleteByArchivoId(@Param("carpetaId") Long carpetaId, @Param("archivoId") Long archivoId);


    List<ArchivoCarpeta> findByCarpetaId(Long carpetaId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ArchivoCarpeta ac WHERE ac.carpeta.id = :carpetaId AND ac.archivo.id = :archivoId")
    void deleteByArchivo(@Param("carpetaId") Long carpetaId, @Param("archivoId") Long archivoId);
}
