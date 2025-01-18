package org.example.switchbox.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "limite_espacio", nullable = false)
    private Long limite_espacio;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Usuario> usuarios;

    public Cuenta() {

    }
    public Cuenta(Long id, String nombre, Long limite_espacio) {
        this.id = id;
        this.nombre = nombre;
        this.limite_espacio = limite_espacio;
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

    public Long getLimite_espacio() {
        return limite_espacio;
    }

    public void setLimite_espacio(Long limite_espacio) {
        this.limite_espacio = limite_espacio;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", limite_espacio=" + limite_espacio +
                '}';
    }
}
