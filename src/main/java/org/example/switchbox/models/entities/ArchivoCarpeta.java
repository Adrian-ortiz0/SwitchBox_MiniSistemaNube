package org.example.switchbox.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "archivo_carpeta")
public class ArchivoCarpeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "archivo_id", nullable = false)
    private Archivo archivo;

    @ManyToOne
    @JoinColumn(name = "carpeta_id", nullable = false)
    private Carpeta carpeta;

    public ArchivoCarpeta() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    public Carpeta getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(Carpeta carpeta) {
        this.carpeta = carpeta;
    }
}
