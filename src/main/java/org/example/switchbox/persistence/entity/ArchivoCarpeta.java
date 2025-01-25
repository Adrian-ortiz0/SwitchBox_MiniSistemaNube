package org.example.switchbox.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "archivo_carpeta")
public class ArchivoCarpeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "archivo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Archivo archivo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carpeta_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Carpeta carpeta;

    public ArchivoCarpeta() {

    }

    public ArchivoCarpeta(Archivo archivo, Carpeta carpeta) {
        this.archivo = archivo;
        this.carpeta = carpeta;
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
