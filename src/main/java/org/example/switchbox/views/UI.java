package org.example.switchbox.views;

import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class UI {
    private Scanner scanner;
    private final RegistroUI registroUI;
    private final InicioSesionUI inicioSesionUI;

    public UI(RegistroUI registroUI, InicioSesionUI inicioSesionUI) {
        scanner = new Scanner(System.in);
        this.registroUI = registroUI;
        this.inicioSesionUI = inicioSesionUI;
    }

    public void start(){
        try{
            while(true){
                System.out.println("Bienvenido a SwitchBox!");
                System.out.println("=======================");
                System.out.println();
                System.out.println("Que deseas hacer?");
                System.out.println("1. Registrarme");
                System.out.println("2. Iniciar Sesion");
                System.out.println("0. Salir");
                try{
                    int input = Integer.parseInt(scanner.nextLine());
                    if(input == 1){
                        registroUI.registroMenu();
                    } else if(input == 2){
                        inicioSesionUI.inicioSesion();
                    } else if(input == 0){
                        System.out.println("Saliendo...");
                        break;
                    } else {
                        System.out.println("Opcion no disponible");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Introduzca un valor valido");
                }

            }
        } catch (Exception e){
            System.out.println("Error" + e.getMessage());
            e.printStackTrace();
        }
    }
}
