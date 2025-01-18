package org.example.switchbox.views;

import org.example.switchbox.controller.CarpetaService;
import org.example.switchbox.models.entities.Carpeta;
import org.example.switchbox.models.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CarpetasUI {
    private Scanner scanner;

    @Autowired
    private CarpetaService carpetaService;

    public CarpetasUI() {
        this.scanner = new Scanner(System.in);
    }

    public void carpetasMenu(Usuario usuario) {
        while (true) {
            System.out.println("Bienvenido al gestor de carpetas!");
            System.out.println("=================================");
            System.out.println();
            System.out.println("1. Crear carpeta");
            System.out.println("2. Eliminar carpeta");
            System.out.println("3. Editar nombre");
            System.out.println("4. Mostrar las carpetas");
            System.out.println("0. Salir");

            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 0) {
                    System.out.println("Saliendo...");
                    break;
                } else if (input == 1) {
                    crearCarpeta(usuario);
                } else if (input == 2) {
                    eliminarCarpeta(usuario);
                } else if (input == 3) {
                    editarCarpeta(usuario);
                } else if (input == 4) {
                    mostrarCarpetas(usuario);
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un valor valido");
            }
        }
    }

    public void mostrarCarpetas(Usuario usuario) {
        List<Carpeta> carpetas = carpetaService.findCarpetasByUsuario(usuario);
        for (int i = 0; i < carpetas.size(); i++) {
            Carpeta carpeta = carpetas.get(i);
            System.out.println((i + 1) + ". " + carpeta.getNombre() + " Tamaño: " + carpeta.getTamaño() + "Bytes");
        }
    }

    public void editarCarpeta(Usuario usuario) {
        while (true) {
            System.out.println("Elija la carpeta que desee editar (0 para salir)");
            List<Carpeta> carpetas = carpetaService.findCarpetasByUsuario(usuario);
            if (carpetas.isEmpty()) {
                System.out.println("No hay carpetas. Saliendo...");
                break;
            } else {
                for (int i = 0; i < carpetas.size(); i++) {
                    Carpeta c = carpetas.get(i);
                    System.out.println((i + 1) + ". " + c.getNombre());
                }
                int carpetaEleccion = Integer.parseInt(scanner.nextLine());
                if (carpetaEleccion == 0) {
                    System.out.println("Saliendo...");
                } else {
                    Carpeta carpetaElegida = carpetas.get(carpetaEleccion - 1);
                    System.out.println("Ingres el nuevo nombre para la carpeta: ");
                    String nuevoNombre = scanner.nextLine();
                    carpetaService.updateNombreCarpeta(carpetaElegida, nuevoNombre, usuario);
                }
            }

        }
    }

    public void eliminarCarpeta(Usuario usuario) {
        while (true) {
            System.out.println("Elija la carpeta que desee eliminar (0 para salir)");
            List<Carpeta> carpetas = carpetaService.findCarpetasByUsuario(usuario);
            if (carpetas.isEmpty()) {
                System.out.println("No hay carpetas. Saliendo...");
                break;
            } else {
                for (int i = 0; i < carpetas.size(); i++) {
                    Carpeta c = carpetas.get(i);
                    System.out.println((i + 1) + ". " + c.getNombre());
                }
                int carpetaEleccion = Integer.parseInt(scanner.nextLine());
                if (carpetaEleccion == 0) {
                    System.out.println("Saliendo...");
                } else {
                    Carpeta carpetaElegida = carpetas.get(carpetaEleccion - 1);
                    carpetaService.deleteCarpeta(carpetaElegida);
                    System.out.println("Carpeta eliminada con exito");
                    break;
                }

            }
        }
    }

    public void crearCarpeta(Usuario usuario) {
        System.out.println("Ingrese el nombre de la carpeta");
        String nombre = scanner.nextLine();
        System.out.println("Deseas guardar esta carpeta dentro de otra carpeta? (1.Si/2.No)");
        int eleccion = Integer.parseInt(scanner.nextLine());
        if (eleccion == 2) {

            Carpeta carpeta = new Carpeta();
            carpeta.setNombre(nombre);
            carpeta.setTamaño(0);
            carpeta.setSubCarpetas(new ArrayList<>());
            carpeta.setUsuario(usuario);

            carpetaService.save(carpeta);
            System.out.println("Carpeta creada con exito!");

        } else if (eleccion == 1) {
            Carpeta carpeta = new Carpeta();
            System.out.println("Elija en que carpeta desea guardar esta carpeta: ");
            List<Carpeta> carpetas = carpetaService.findCarpetasByUsuario(usuario);

            if (carpetas.isEmpty()) {
                System.out.println("No hay carpetas creadas!");
                System.out.println("Se procedera a guardar la carpeta en la raiz automaticamente!");
                System.out.println();
                carpeta.setNombre(nombre);
                carpeta.setTamaño(0);
                carpeta.setSubCarpetas(new ArrayList<>());
                carpeta.setUsuario(usuario);
                carpetaService.save(carpeta);
                System.out.println("Carpeta creada con exito!");

                return;
            } else {
                for (int i = 0; i < carpetas.size(); i++) {
                    Carpeta c = carpetas.get(i);
                    System.out.println((i + 1) + ". " + c.getNombre());
                }
                int carpetaEleccion = Integer.parseInt(scanner.nextLine());
                Carpeta carpetaPadreElegida = carpetas.get(carpetaEleccion - 1);

                carpeta.setCarpetaPadre(carpetaPadreElegida);
                carpeta.setNombre(nombre);
                carpeta.setSubCarpetas(new ArrayList<>());
                carpeta.setTamaño(0);
                carpeta.setUsuario(usuario);

                carpetaService.save(carpeta);
                System.out.println("Sub carpeta guardada con exito!");

            }
        }
    }
}
