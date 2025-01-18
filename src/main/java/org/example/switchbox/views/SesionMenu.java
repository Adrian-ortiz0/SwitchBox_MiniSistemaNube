package org.example.switchbox.views;

import org.example.switchbox.models.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SesionMenu {

    @Autowired
    private ArchivosUI archivosUI;

    @Autowired
    private CarpetasUI carpetasUI;

    @Autowired
    private UsuariosUI usuariosUI;

    private Scanner scanner;

    public SesionMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void menu(Usuario usuario) {

        while (true) {
            System.out.println("Que deseas hacer?: ");
            System.out.println("1. Gestion de archivos");
            System.out.println("2. Gestion de carpetas");
            System.out.println("3. Editar perfil");
            System.out.println("0. Salir");
            try{
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 1) {
                    archivosUI.archivosMenu(usuario);
                } else if (input == 2) {
                    carpetasUI.carpetasMenu(usuario);
                } else if (input == 3) {
                    usuariosUI.usuariosMenu(usuario);
                } else if (input == 0) {
                    System.out.println("Saliendo...");
                    break;
                } else {
                    System.out.println("Opcion no valida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un valor valido");
                continue;
            }

        }
    }
}
