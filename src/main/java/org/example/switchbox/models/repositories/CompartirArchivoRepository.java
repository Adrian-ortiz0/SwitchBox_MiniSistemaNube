package org.example.switchbox.models.repositories;

import org.example.switchbox.models.entities.CompartirArchivo;
import org.example.switchbox.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompartirArchivoRepository extends JpaRepository<CompartirArchivo, Long> {

    @Query("select ca from CompartirArchivo ca join fetch ca.archivo a join fetch ca.usuarioOrigen uo where ca.usuarioDestino = :usuario")
    List<CompartirArchivo> findArchivosCompartidosConmigo(Usuario usuario);
}
