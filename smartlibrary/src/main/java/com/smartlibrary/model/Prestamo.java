package com.smartlibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion; // vacia (null) mientras el prestamo este activo

    // Asociacion Prestamo 0..* --> 1 Estudiante
    @ManyToOne(optional = false)
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    // Asociacion Prestamo 0..* --> 1 Libro
    @ManyToOne(optional = false)
    @JoinColumn(name = "libro_id")
    private Libro libro;

    protected Prestamo() {
    }

    // R2: el prestamo registra estudiante, libro y fecha
    public Prestamo(Estudiante estudiante, Libro libro, LocalDate fechaPrestamo) {
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Libro getLibro() {
        return libro;
    }

    // R5: un prestamo esta activo mientras no tenga fecha de devolucion
    public boolean estaActivo() {
        return fechaDevolucion == null;
    }
}
