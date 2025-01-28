package org.example.switchbox.domain.service;

import org.example.switchbox.Exceptions.CuentaNotFoundException;
import org.example.switchbox.persistence.entity.Cuenta;
import org.example.switchbox.domain.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<Cuenta> listaCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta guardaCuenta(Cuenta cuenta) {
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula.");
        }
        return cuentaRepository.save(cuenta);
    }

    @Override
    public void eliminaCuenta(long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con id :: " + id));
        cuentaRepository.delete(cuenta);
    }

    @Override
    public Cuenta getCuentaById(long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con id :: " + id));
    }

    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

}
