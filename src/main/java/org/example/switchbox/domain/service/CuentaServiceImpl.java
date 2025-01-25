package org.example.switchbox.domain.service;

import org.example.switchbox.persistence.entity.Cuenta;
import org.example.switchbox.domain.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Override
    public List<Cuenta> listaCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta guardaCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public void eliminaCuenta(long id) {

    }

    @Override
    public Cuenta getCuentaById(long id) {
        return null;
    }

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

}
