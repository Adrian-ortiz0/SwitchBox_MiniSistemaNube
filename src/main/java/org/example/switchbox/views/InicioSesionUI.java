package org.example.switchbox.views;

import org.example.switchbox.controller.UsuarioService;
import org.example.switchbox.models.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InicioSesionUI {

    @Autowired
    private SesionMenu sesionMenu;

    private Scanner scanner;
    @Autowired
    private UsuarioService usuarioService;

    public InicioSesionUI(SesionMenu sesionMenu) {
        this.scanner = new Scanner(System.in);
        this.sesionMenu = sesionMenu;
    }

    public void inicioSesion() {
        try{
            System.out.println("Ingresar tu email: ");
            String email = this.scanner.nextLine();
            System.out.println("Ingresar tu password: ");
            String password = this.scanner.nextLine();
            Usuario usuario = usuarioService.findByEmailAndPassword(email, password);
            if(usuario != null) {
                System.out.println("Bienvenido Usuario " + usuario.getNombre());
                sesionMenu.menu(usuario);
            } else {
                System.out.println("Usuario no encontrado. O datos incorrectos!");
            }
        } catch (Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
