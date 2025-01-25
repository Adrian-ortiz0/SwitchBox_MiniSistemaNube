package org.example.switchbox.domain.service;

import org.example.switchbox.persistence.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();

    Usuario findById(long id);

    Usuario save(Usuario usuario);

    void delete(long id);

    Usuario updateUsuario(Long id, Usuario usuarioDetails);

    void updateEspacio(Long espacio, Long id);


}
