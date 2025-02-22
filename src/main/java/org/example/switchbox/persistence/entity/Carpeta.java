package org.example.switchbox.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "carpetas")
public class Carpeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tamaño", nullable = false)
    private long tamaño = 0;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "carpeta_padre_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Carpeta carpetaPadre;

    @JsonIgnore
    @OneToMany(mappedBy = "carpetaPadre", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carpeta> subCarpetas;

    public Carpeta() {

    }

    public Carpeta(long id, String nombre, long tamaño, Usuario usuario, Carpeta carpetaPadre, List<Carpeta> subCarpetas) {
        this.id = id;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.usuario = usuario;
        this.carpetaPadre = carpetaPadre;
        this.subCarpetas = subCarpetas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Carpeta getCarpetaPadre() {
        return carpetaPadre;
    }

    public void setCarpetaPadre(Carpeta carpetaPadre) {
        this.carpetaPadre = carpetaPadre;
    }

    public List<Carpeta> getSubCarpetas() {
        return subCarpetas;
    }

    public void setSubCarpetas(List<Carpeta> subCarpetas) {
        this.subCarpetas = subCarpetas;
    }

    @Override
    public String toString() {
        return "Carpeta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tamaño=" + tamaño +
                ", usuario=" + usuario.getNombre() +
                ", carpetaPadre=" + (carpetaPadre != null ? carpetaPadre.getNombre() : "N/A") +
                '}';
    }
}
