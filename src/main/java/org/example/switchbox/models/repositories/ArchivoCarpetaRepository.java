package org.example.switchbox.models.repositories;

import org.example.switchbox.models.entities.ArchivoCarpeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArchivoCarpetaRepository extends JpaRepository<ArchivoCarpeta, Long> {


}
