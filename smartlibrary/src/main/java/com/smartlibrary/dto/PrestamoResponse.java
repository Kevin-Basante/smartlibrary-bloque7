package com.smartlibrary.dto;

import com.smartlibrary.model.Prestamo;
import java.time.LocalDate;

// Lo que la API devuelve en JSON (no exponemos la entidad directamente)
public class PrestamoResponse {

    private Long id;
    private Long estudianteId;
    private String nombreEstudiante;
    private Long libroId;
    private String tituloLibro;
    private LocalDate fechaPrestamo;
    private boolean activo;

    public static PrestamoResponse desde(Prestamo prestamo) {
        PrestamoResponse r = new PrestamoResponse();
        r.id = prestamo.getId();
        r.estudianteId = prestamo.getEstudiante().getId();
        r.nombreEstudiante = prestamo.getEstudiante().getNombre();
        r.libroId = prestamo.getLibro().getId();
        r.tituloLibro = prestamo.getLibro().getTitulo();
        r.fechaPrestamo = prestamo.getFechaPrestamo();
        r.activo = prestamo.estaActivo();
        return r;
    }

    public Long getId() { return id; }
    public Long getEstudianteId() { return estudianteId; }
    public String getNombreEstudiante() { return nombreEstudiante; }
    public Long getLibroId() { return libroId; }
    public String getTituloLibro() { return tituloLibro; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public boolean isActivo() { return activo; }
}
