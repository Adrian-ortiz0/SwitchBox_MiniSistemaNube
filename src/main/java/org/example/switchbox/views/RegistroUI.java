package org.example.switchbox.views;

import org.example.switchbox.controller.CuentaService;
import org.example.switchbox.controller.UsuarioService;
import org.example.switchbox.models.entities.Cuenta;
import org.example.switchbox.models.entities.Usuario;
import org.example.switchbox.models.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Component
public class RegistroUI {
    private Scanner scanner;

    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private UsuarioService usuarioService;

    public RegistroUI() {
       this.scanner = new Scanner(System.in);
    }

    public void registroMenu(){
        try{
            while(true){
                System.out.println("Bienvenido al sistema de registro!");
                System.out.println("==================================");

                System.out.println();

                System.out.println("Ingresa tu nombre: ");
                String nombre = scanner.nextLine();

                System.out.println("Ingresa tu apellido: ");
                String apellido = scanner.nextLine();

                System.out.println("Ingresa tu email: ");
                String email = scanner.nextLine();

                System.out.println("Crea una contraseña: ");
                String password = scanner.nextLine();

                System.out.println("Selecciona el tipo de cuenta que deseas: ");
                List<Cuenta> cuentas = cuentaService.findAll();
                for(int i = 0; i < cuentas.size(); i++){
                    Cuenta cuenta = cuentas.get(i);
                    double limiteEspacioGB = cuenta.getLimite_espacio() / (1024.0 * 1024.0 * 1024.0);
                    System.out.println((i + 1) + ". " + cuenta.getNombre() + " Espacio: " + String.format("%.2f", limiteEspacioGB) + "GB");
                }
                int eleccionCuenta = Integer.parseInt(scanner.nextLine());
                Cuenta cuentaSeleccionada = cuentas.get(eleccionCuenta - 1);

                Usuario usuario = new Usuario();
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setEmail(email);
                usuario.setPassword(password);
                usuario.setEspacioUsado(0);
                usuario.setCuenta(cuentaSeleccionada);
                Usuario usuarioGuardad = usuarioService.save(usuario);
                if(usuarioGuardad == null){
                    System.out.println("Usuario no guardado");
                } else {
                    System.out.println("Usuario guardado con exito!!");
                    break;
                }
            }

        } catch (Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
