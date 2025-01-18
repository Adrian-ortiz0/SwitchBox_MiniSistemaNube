package org.example.switchbox.views;

import org.example.switchbox.controller.ArchivoService;
import org.example.switchbox.controller.CuentaService;
import org.example.switchbox.controller.UsuarioService;
import org.example.switchbox.models.entities.Archivo;
import org.example.switchbox.models.entities.CompartirArchivo;
import org.example.switchbox.models.entities.TipoArchivo;
import org.example.switchbox.models.entities.Usuario;
import org.example.switchbox.models.repositories.ArchivoRepository;
import org.example.switchbox.models.repositories.CuentaRepository;
import org.example.switchbox.models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ArchivosUI {

    private Scanner scanner;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private ArchivoRepository archivoRepository;

    public ArchivosUI() {
        this.scanner = new Scanner(System.in);
    }

    public void archivosMenu(Usuario usuario) {
        while (true) {
            System.out.println("Bienvenido al gestor de archivos!");
            System.out.println("=================================");
            System.out.println();
            System.out.println("1. Subir un archivo");
            System.out.println("2. Ver los archivos");
            System.out.println("3. Eliminar un archivo");
            System.out.println("4. Compartir un archivo");
            System.out.println("5. Editar un archivo");
            System.out.println("6. Ver Archivos compartidos");
            System.out.println("7. Buscar archivo por nombre");
            System.out.println("0. Salir");

            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 0) {
                    System.out.println("Salir");
                    break;
                } else if (input == 1) {
                    subirArchivo(usuario);
                } else if (input == 2) {
                    listarArchivos(usuario);
                } else if (input == 3) {
                    eliminarArchivo(usuario);
                } else if (input == 4) {
                    compartirArchivo(usuario);
                } else if (input == 5) {
                    editarNombreArchivo(usuario);
                } else if (input == 6) {
                    listarArchivosCompartidos(usuario);
                } else if (input == 7) {
                    buscarArchivoPorNombre(usuario);
                } else {
                    System.out.println("Opcion no valida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un valor valido");
                continue;
            }
        }
    }

    public void buscarArchivoPorNombre(Usuario usuario) {
        System.out.println("Ingrese el nombre del archivo a buscar: ");
        String nombreArchivo = scanner.nextLine().trim();

        if (nombreArchivo.isEmpty()) {
            System.out.println("El nombre del archivo no puede estar vacío.");
            return;
        }

        Optional<Archivo> archivoOpt = archivoRepository.findByNombre(nombreArchivo);

        if (archivoOpt.isPresent()) {
            Archivo archivo = archivoOpt.get();
            System.out.println("Archivo encontrado: " + archivo.getNombre() + " | Tamaño: " + archivo.getTamaño() + " bytes.");
        } else {
            System.out.println("No se encontró ningún archivo con el nombre: " + nombreArchivo);
        }
    }

    public void listarArchivosCompartidos(Usuario usuario) {
        List<CompartirArchivo> archivosCompartidos = archivoService.getCompartirArchivos(usuario);
        int contador = 1;
        for (CompartirArchivo compartirArchivo : archivosCompartidos) {
            System.out.println(contador + ". " + compartirArchivo.getArchivo().getNombre() + "| Propietario: " + compartirArchivo.getUsuarioOrigen().getEmail());
            contador++;
        }
    }

    public void compartirArchivo(Usuario usuario) {
        List<Archivo> listaArchivos = archivoService.getArchivos(usuario.getId());
        System.out.println("Selecciona el archivo que deseas enviar");
        if (listaArchivos.isEmpty()) {
            System.out.println("El archivo no existe");
            return;
        } else {
            for (int i = 0; i < listaArchivos.size(); i++) {
                Archivo archivo = listaArchivos.get(i);
                System.out.println((i + 1) + ". " + archivo.getNombre() + "| Tamaño: " + archivo.getTamaño());
            }
            int archivoEleccion = Integer.parseInt(scanner.nextLine());
            Archivo archivoElegido = listaArchivos.get(archivoEleccion - 1);
            System.out.println("Archivo seleccionado!");
            System.out.println();
            System.out.println("Ingrese el correo de la persona con la que compartirá el archivo: ");
            String correo = scanner.nextLine();
            Usuario destinatario = usuarioService.getUsuarioByEmail(correo);

            if (destinatario == null) {
                System.out.println("El usuario no existe o esta mal el correo");
            } else {
                CompartirArchivo compartirArchivo = new CompartirArchivo();
                compartirArchivo.setArchivo(archivoElegido);
                compartirArchivo.setUsuarioDestino(destinatario);
                compartirArchivo.setUsuarioOrigen(usuario);
                compartirArchivo.setFechaCompartido(LocalDateTime.now());

                archivoService.compartirArchivo(compartirArchivo);
            }
        }
    }

    public void editarNombreArchivo(Usuario usuario) {
        List<Archivo> listaArchivos = archivoService.getArchivos(usuario.getId());
        System.out.println("Selecciona el archivo que deseas editar");
        if (listaArchivos.isEmpty()) {
            System.out.println("El archivo no existe");
            return;
        } else {
            for (int i = 0; i < listaArchivos.size(); i++) {
                Archivo archivo = listaArchivos.get(i);
                System.out.println((i + 1) + ". " + archivo.getNombre() + "| Tamaño: " + archivo.getTamaño());
            }
            int archivoEleccion = Integer.parseInt(scanner.nextLine());
            Archivo archivoElegido = listaArchivos.get(archivoEleccion - 1);
            System.out.println("Archivo seleccionado");
            System.out.println();
            System.out.println("Escriba un nuevo nombre para el archivo");
            String nuevoNombreArchivo = scanner.nextLine();
            if (nuevoNombreArchivo.isBlank()) {
                System.out.println("No se realizó el cambio");
                return;
            } else {
                archivoService.editarNombreArchivo(archivoElegido.getId(), nuevoNombreArchivo);
            }
        }
    }

    public void eliminarArchivo(Usuario usuario) {
        List<Archivo> listaArchivos = archivoService.getArchivos(usuario.getId());
        if (listaArchivos.isEmpty()) {
            System.out.println("El archivo no existe");
            return;
        } else {
            for (int i = 0; i < listaArchivos.size(); i++) {
                Archivo archivo = listaArchivos.get(i);
                System.out.println((i + 1) + archivo.getNombre() + " Tamaño: " + archivo.getTamaño());
            }
            int archivoEleccion = Integer.parseInt(scanner.nextLine());
            Archivo archivoElegido = listaArchivos.get(archivoEleccion - 1);
            archivoService.deleteArchivoById(archivoElegido.getId());
        }
    }

    public List<Archivo> listarArchivos(Usuario usuario) {
        List<Archivo> archivos = archivoService.getArchivos(usuario.getId());
        if (archivos.isEmpty()) {
            System.out.println("No hay archivos");
        }
        for (Archivo archivo : archivos) {
            System.out.println(archivo);
        }
        return archivos;

    }

    public void subirArchivo(Usuario usuario) {
        while (true) {
            try {
                System.out.println("Ingresa el nombre del archivo: ");
                String nombreArchivo = scanner.nextLine();
                if (nombreArchivo.trim().isEmpty()) {
                    System.out.println("El nombre del archivo no puede estar vacío.");
                    continue;
                }

                System.out.println("Ingresa el tipo de archivo: ");
                for (int i = 0; i < TipoArchivo.values().length; i++) {
                    TipoArchivo tipoArchivo = TipoArchivo.values()[i];
                    System.out.println((i + 1) + ". " + tipoArchivo.name());
                }

                int opcionTipo = Integer.parseInt(scanner.nextLine());
                if (opcionTipo < 1 || opcionTipo > TipoArchivo.values().length) {
                    System.out.println("Opción inválida. Por favor, elige una opción válida.");
                    continue;
                }

                TipoArchivo tipoArchivo = TipoArchivo.values()[opcionTipo - 1];

                System.out.println("Ingresa el tamaño del archivo en bytes: ");
                long tamaño = Long.parseLong(scanner.nextLine());

                if (tamaño <= 0) {
                    System.out.println("El tamaño del archivo debe ser mayor a cero.");
                    continue;
                }

                Archivo archivo = new Archivo();
                archivo.setNombre(nombreArchivo);
                archivo.setTipo(tipoArchivo);
                archivo.setFecha_subida(LocalDate.now());
                archivo.setTamaño(tamaño);
                archivo.setUsuario(usuario);

                if (tamaño >= usuario.getCuenta().getLimite_espacio()) {
                    System.out.println("Espacio de almacenamiento insuficiente para contener el archivo!");
                    return;
                }

                Archivo archivoSubido = archivoService.save(archivo);
                if (archivoSubido != null) {
                    System.out.println("¡Archivo subido con éxito!");
                    System.out.println(tamaño);
                    usuarioService.updateEspacio(usuario.getEspacioUsado() + tamaño, usuario.getId());
                } else {
                    System.out.println("Error al subir el archivo. Intenta nuevamente.");
                }

                System.out.println("Espacio Actual: " + usuarioService.getEspacio(usuario.getId()) + "| Espacio disponible: " + usuario.getCuenta().getLimite_espacio());
                System.out.println("¿Deseas subir otro archivo? (1. Sí / 2. No)");
                int continuar = Integer.parseInt(scanner.nextLine());
                if (continuar != 1) {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Introduzca un valor numérico válido. " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            }
        }
    }
}
