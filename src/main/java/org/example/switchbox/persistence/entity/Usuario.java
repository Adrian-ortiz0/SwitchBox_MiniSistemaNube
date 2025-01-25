package org.example.switchbox.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "espacio_usado", nullable = false)
    private Long espacioUsado;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id")
    private Cuenta cuenta;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Archivo> archivos;


    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Carpeta> carpetas;


    public Usuario() {

    }

    public Usuario(Long id, String nombre, String apellido, String email, String password, Long espacioUsado, Cuenta cuenta, List<Archivo> archivos, List<Carpeta> carpetas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.espacioUsado = espacioUsado;
        this.cuenta = cuenta;
        this.archivos = archivos;
        this.carpetas = carpetas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getEspacioUsado() {
        return espacioUsado;
    }

    public void setEspacioUsado(long espacio_usado) {
        this.espacioUsado = espacio_usado;
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    public List<Carpeta> getCarpetas() {
        return carpetas;
    }

    public void setCarpetas(List<Carpeta> carpetas) {
        this.carpetas = carpetas;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", espacioUsado=" + espacioUsado +
                ", cuenta=" + cuenta.getNombre() +
                ", archivos=" + (archivos != null ? archivos.size() : 0) +
                ", carpetas=" + (carpetas != null ? carpetas.size() : 0) +
                '}';
    }
}
