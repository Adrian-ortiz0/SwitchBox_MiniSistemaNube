package org.example.switchbox.models.repositories;

import jakarta.transaction.Transactional;
import org.example.switchbox.models.entities.Archivo;
import org.example.switchbox.models.entities.Cuenta;
import org.example.switchbox.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByEmailAndPassword(String email, String password);

    @Modifying
    @Transactional
    @Query("update Usuario u set u.nombre = :nombre, u.apellido = :apellido where u.id = :id")
    void updateNombreAndApellido(long id, String nombre, String apellido);

    @Modifying
    @Transactional
    @Query("update Usuario  u set u.email = :email where u.id = :id")
    void updateEmail(long id, String email);

    @Modifying
    @Transactional
    @Query("update Usuario u set u.password = :password where u.id = :id")
    void updatePassword(long id, String password);

    public Usuario findByPassword(String password);

    @Modifying
    @Transactional
    @Query("update Usuario u set u.cuenta = :cuenta where u.id = :id")
    void updateCuenta(long id, Cuenta cuenta);

    @Modifying
    @Transactional
    @Query("delete from Usuario u where u.password = :password")
    void deleteByPassword(String password);

    @Modifying
    @Transactional
    @Query("update Usuario u set u.espacioUsado = :espacio where u.id = :id")
    void updateEspacio(long espacio, long id);

    @Query("select u.espacioUsado from Usuario u where u.id = :id")
    long getEspacioById(long id);

    @Query("select a from Archivo a where a.usuario.id = :id")
    List<Archivo> getArchivosByUsuario(long id);

    Usuario findByEmail(String email);

}
