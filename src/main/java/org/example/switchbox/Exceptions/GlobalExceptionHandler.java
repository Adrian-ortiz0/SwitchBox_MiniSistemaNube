package org.example.switchbox.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponses> handleProductoNotFoundException(ProductoNotFoundException ex) {
        ErrorResponses errorResponse = new ErrorResponses("Producto no encontrado", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UsuarioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponses> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        ErrorResponses errorResponse = new ErrorResponses("Usuario no encontrado", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CuentaNotFoundException.class)
    public ResponseEntity<String> handleCuentaNotFound(CuentaNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponses> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponses errorResponse = new ErrorResponses("Solicitud inválida", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponses> handleGlobalException(Exception ex) {
        ErrorResponses errorResponse = new ErrorResponses("Error en la aplicación", "Ocurrió un error inesperado. Por favor, inténtelo más tarde.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
