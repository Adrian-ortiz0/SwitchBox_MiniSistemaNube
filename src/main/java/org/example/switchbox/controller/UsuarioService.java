package org.example.switchbox.controller;

import jakarta.transaction.Transactional;
import org.example.switchbox.models.entities.Archivo;
import org.example.switchbox.models.entities.Cuenta;
import org.example.switchbox.models.entities.Usuario;
import org.example.switchbox.models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CuentaService cuentaService;

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario findByPassword(String password) {
        return usuarioRepository.findByPassword(password);
    }

    public void actualizarPassword(long id, String password) {
        usuarioRepository.updatePassword(id, password);
        System.out.println("Usuario actualizado con exito!");
    }

    public Usuario findByEmailAndPassword(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password);
    }

    public void actualizarNombreYApellido(Long id, String nombre, String apellido) {
        usuarioRepository.updateNombreAndApellido(id, nombre, apellido);
        System.out.println("Usuario actualizado con exito!");
    }

    public void actualizarCorreo(Long id, String correo) {
        usuarioRepository.updateEmail(id, correo);
        System.out.println("Usuario actualizado con exito!");
    }

    public void actualizarCuenta(Long id, Cuenta cuenta) {
        usuarioRepository.updateCuenta(id, cuenta);
        System.out.println("Cuenta actualizada con exito!");
    }

    public void deleteByPassword(String password) {
        usuarioRepository.deleteByPassword(password);
    }

    public void updateEspacio(long espacio, long id) {
        usuarioRepository.updateEspacio(espacio, id);
        usuarioRepository.flush();
        System.out.println("Espacio actualizado con exito!");
    }
    public long getEspacio(long id) {
      return usuarioRepository.getEspacioById(id);
    }

    public Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

}
