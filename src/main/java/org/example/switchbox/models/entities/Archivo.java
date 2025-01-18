package org.example.switchbox.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "archivos")
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoArchivo tipo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_subida")
    private LocalDate fecha_subida;

    @Column(name = "tamaño", nullable = false)
    private Long tamaño;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Archivo() {

    }

    public Archivo(Long id, String nombre, TipoArchivo tipo, LocalDate fecha_subida, long tamaño, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha_subida = fecha_subida;
        this.tamaño = tamaño;
        this.usuario = usuario;
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

    public TipoArchivo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArchivo tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha_subida() {
        return fecha_subida;
    }

    public void setFecha_subida(LocalDate fecha_subida) {
        this.fecha_subida = fecha_subida;
    }

    public long getTamaño() {
        return tamaño;
    }

    public void setTamaño(long tamaño) {
        this.tamaño = tamaño;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Archivo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", fecha_subida=" + fecha_subida +
                ", tamaño=" + tamaño +
                ", usuario=" + usuario.getNombre() +
                '}';
    }
}
