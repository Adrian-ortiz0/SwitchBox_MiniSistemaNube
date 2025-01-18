package org.example.switchbox.models.repositories;

import org.example.switchbox.models.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {


}
