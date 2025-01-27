package org.example.switchbox.persistence.entity;

public class RegisterUser {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Long cuentaId;
    private Long espacioUsado;

    public RegisterUser() {
    }

    public RegisterUser(String nombre, String apellido, String email, String password, Long cuentaId, Long espacioUsado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.cuentaId = cuentaId;
        this.espacioUsado = espacioUsado;
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

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public Long getEspacioUsado() {
        return espacioUsado;
    }

    public void setEspacioUsado(Long espacioUsado) {
        this.espacioUsado = espacioUsado;
    }
}
