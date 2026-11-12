package com.smartlibrary.controller;

import com.smartlibrary.exception.LibroNoDisponibleException;
import com.smartlibrary.exception.RecursoNoEncontradoException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Convierte las excepciones del negocio en codigos HTTP
@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> manejarNoEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(LibroNoDisponibleException.class)
    public ResponseEntity<String> manejarNoDisponible(LibroNoDisponibleException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> manejarDatosInvalidos(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos invalidos: faltan estudianteId o libroId");
    }
}
