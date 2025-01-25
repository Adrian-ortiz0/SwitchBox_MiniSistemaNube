package org.example.switchbox.persistence.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "compartir_archivo")
public class CompartirArchivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "archivo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Archivo archivo;

    @ManyToOne
    @JoinColumn(name = "usuario_origen_id", nullable = false)
    private Usuario usuarioOrigen;

    @ManyToOne
    @JoinColumn(name = "usuario_destino_id", nullable = false)
    private Usuario usuarioDestino;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCompartido;

    public CompartirArchivo() {

    }

    @PrePersist
    public void prePersist() {
        if (this.fechaCompartido == null) {
            this.fechaCompartido = LocalDateTime.now();
        }
    }

    public Usuario getUsuarioOrigen() {
        return usuarioOrigen;
    }

    public void setUsuarioOrigen(Usuario usuarioOrigen) {
        this.usuarioOrigen = usuarioOrigen;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    public LocalDateTime getFechaCompartido() {
        return fechaCompartido;
    }

    public void setFechaCompartido(LocalDateTime fechaCompartido) {
        this.fechaCompartido = fechaCompartido;
    }

    @Override
    public String toString() {
        return "CompartirArchivo{" +
                "id=" + id +
                ", archivo=" + archivo.getNombre() + // Mostrar el nombre del archivo
                ", usuarioOrigen=" + usuarioOrigen.getNombre() +
                ", usuarioDestino=" + usuarioDestino.getNombre() +
                ", fechaCompartido=" + fechaCompartido +
                '}';
    }
}
