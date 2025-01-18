package org.example.switchbox.views;

import org.example.switchbox.controller.CuentaService;
import org.example.switchbox.controller.UsuarioService;
import org.example.switchbox.models.entities.Cuenta;
import org.example.switchbox.models.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class UsuariosUI {
    private Scanner scanner;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CuentaService cuentaService;

    public UsuariosUI() {
        this.scanner = new Scanner(System.in);
    }

    public void usuariosMenu(Usuario usuario) {
        while (true) {
            System.out.println("Bienvenido " + usuario.getNombre() + " " + usuario.getApellido());
            System.out.println("===============================================================");
            System.out.println();
            System.out.println("1. Cambiar nombre y apellido");
            System.out.println("2. Cambiar email");
            System.out.println("3. Cambiar contraseña");
            System.out.println("4. Cambiar de suscripción");
            System.out.println("5. Borrar la cuenta");
            System.out.println("0. Salir");

            try{
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 0) {
                    System.out.println("Saliendo...");
                    break;
                } else if (input == 1) {

                    System.out.println("Ingresa un nuevo nombre: (Nombre Actual: " + usuario.getNombre() + ")");
                    String nuevoNombre = scanner.nextLine();

                    System.out.println("Ingresa un nuevo apellido: (Apellido Actual: " + usuario.getApellido() + ")");
                    String nuevoApellido = scanner.nextLine();

                    usuarioService.actualizarNombreYApellido(usuario.getId(), nuevoNombre, nuevoApellido);

                } else if (input == 2) {

                    System.out.println("Ingresa un nuevo email: (Email Actual: " + usuario.getEmail() + ")");
                    String nuevoEmail = scanner.nextLine();
                    usuarioService.actualizarCorreo(usuario.getId(), nuevoEmail);

                } else if (input == 3) {

                    System.out.println("Ingresa tu contraseña actual: ");
                    String contraseñaActual = scanner.nextLine();

                    Usuario usuarioEncontrado = usuarioService.findByPassword(contraseñaActual);
                    if (usuarioEncontrado == null) {
                        System.out.println("Contraseña incorrecta");
                        return;
                    } else {
                        System.out.println("Ingresa una nueva contraseña: ");
                        String nuevaContraseña = scanner.nextLine();
                        usuarioService.actualizarPassword(usuario.getId(), nuevaContraseña);
                        break;
                    }

                } else if (input == 4) {
                    System.out.println("Elige la suscripcion que deseas adquirir: (Cuenta actual: " + usuario.getCuenta().getNombre() + ")");

                    List<Cuenta> cuentas = cuentaService.findAll();
                    for (int i = 0; i < cuentas.size(); i++) {
                        Cuenta cuenta = cuentas.get(i);
                        double limiteEspacioGB = cuenta.getLimite_espacio() / (1024.0 * 1024.0 * 1024.0);
                        System.out.println((i + 1) + ". " + cuenta.getNombre() + " Espacio: " + String.format("%.2f", limiteEspacioGB) + "GB");
                    }
                    int eleccionCuenta = Integer.parseInt(scanner.nextLine());
                    Cuenta cuentaElegida = cuentas.get(eleccionCuenta - 1);
                    usuarioService.actualizarCuenta(usuario.getId(), cuentaElegida);

                } else if (input == 5) {
                    while(true){
                        System.out.println("Estas seguro de que deseas eliminar tu cuenta? (1.Si/2.No)");
                        try{
                            int opcion = Integer.parseInt(scanner.nextLine());
                            if (opcion == 1) {
                                System.out.println("Ingresa tu contraseña actual: ");
                                String contraseñaActual = scanner.nextLine();
                                Usuario usuarioEncontrado = usuarioService.findByPassword(contraseñaActual);
                                if (usuarioEncontrado == null) {
                                    System.out.println("Contraseña incorrecta");
                                } else {
                                    System.out.println("Eliminado con exito!");
                                    usuarioService.deleteByPassword(contraseñaActual);
                                    break;
                                }
                            } else if(opcion == 2) {
                                System.out.println("Saliendo...");
                                break;
                            } else {
                                System.out.println("Opcion incorrecta");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Valor invalido!");
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un valor valido!");
            }
        }

    }
}
