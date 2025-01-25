package org.example.switchbox.web.controller;

import org.example.switchbox.domain.service.CuentaService;
import org.example.switchbox.persistence.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public List<Cuenta> getCuentas() {
        return cuentaService.listaCuentas();
    }

    @GetMapping("/{id}")
    public Cuenta getCuentaById(@PathVariable long id) {
        return cuentaService.getCuentaById(id);
    }

    @PostMapping
    public Cuenta addCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.guardaCuenta(cuenta);
    }

    @DeleteMapping("/{id}")
    public void deleteCuentaById(@PathVariable long id) {
        cuentaService.eliminaCuenta(id);
    }

}
