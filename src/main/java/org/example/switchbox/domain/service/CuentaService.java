package org.example.switchbox.domain.service;

import org.example.switchbox.persistence.entity.Cuenta;

import java.util.List;

public interface CuentaService {
    List<Cuenta> listaCuentas();

    Cuenta guardaCuenta(Cuenta cuenta);

    void eliminaCuenta(long id);

    Cuenta getCuentaById(long id);
}
