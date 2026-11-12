package com.smartlibrary.dto;

import jakarta.validation.constraints.NotNull;

// Datos que llegan en el cuerpo de POST /api/prestamos
public class PrestamoRequest {

    @NotNull(message = "estudianteId es obligatorio")
    private Long estudianteId;

    @NotNull(message = "libroId es obligatorio")
    private Long libroId;

    public PrestamoRequest() {
    }

    public PrestamoRequest(Long estudianteId, Long libroId) {
        this.estudianteId = estudianteId;
        this.libroId = libroId;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }
}
