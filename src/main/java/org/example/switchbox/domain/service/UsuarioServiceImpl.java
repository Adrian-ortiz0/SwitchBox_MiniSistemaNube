package org.example.switchbox.domain.service;

import org.example.switchbox.domain.repository.CuentaRepository;
import org.example.switchbox.persistence.entity.Cuenta;
import org.example.switchbox.persistence.entity.RegisterUser;
import org.example.switchbox.persistence.entity.Usuario;
import org.example.switchbox.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado para este id :: " + id));
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Usuario usuario = findById(id);

        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellido(usuarioDetails.getApellido());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setPassword(usuarioDetails.getPassword());

        if (usuarioDetails.getCuenta() != null) {
            usuario.setCuenta(usuarioDetails.getCuenta());
        }

        return usuarioRepository.save(usuario);
    }


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private CuentaServiceImpl cuentaServiceImpl;

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

    public void updateEspacio(Long espacio, Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setEspacioUsado(espacio);
        usuarioRepository.save(usuario);
    }


    public long getEspacio(long id) {
        return usuarioRepository.getEspacioById(id);
    }

    public Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario registrar(RegisterUser registerUser) {
        Cuenta cuenta = cuentaRepository.findById(registerUser.getCuentaId())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con ID: " + registerUser.getCuentaId()));

        Usuario usuario = new Usuario();
        usuario.setNombre(registerUser.getNombre());
        usuario.setApellido(registerUser.getApellido());
        usuario.setEmail(registerUser.getEmail());
        usuario.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        usuario.setEspacioUsado(registerUser.getEspacioUsado());
        usuario.setCuenta(cuenta);

        return usuarioRepository.save(usuario);
    }

    public boolean verificarPorCorreo(String correo, String password) {
        Usuario usuario = getUsuarioByEmail(correo);
        if (usuario == null) {
            return false;
        }
        return passwordEncoder.matches(password, usuario.getPassword());
    }

    public boolean emailExists(String email) {
        return usuarioRepository.findByEmail(email) != null;
    }


}
