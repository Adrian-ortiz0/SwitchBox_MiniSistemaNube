package org.example.switchbox.domain.repository;

import org.example.switchbox.persistence.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
