package com.smartlibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private boolean disponible;

    protected Libro() {
    }

    // Un libro nuevo siempre empieza disponible
    public Libro(String titulo) {
        this.titulo = titulo;
        this.disponible = true;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    // R3: al prestarlo, el libro deja de estar disponible
    public void marcarPrestado() {
        this.disponible = false;
    }
}
