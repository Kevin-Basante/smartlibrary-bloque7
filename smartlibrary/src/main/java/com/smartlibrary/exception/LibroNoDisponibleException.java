package com.smartlibrary.exception;

// Se traduce a 409 Conflict
public class LibroNoDisponibleException extends RuntimeException {

    public LibroNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
