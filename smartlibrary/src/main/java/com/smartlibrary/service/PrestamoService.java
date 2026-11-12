package com.smartlibrary.service;

import com.smartlibrary.dto.PrestamoRequest;
import com.smartlibrary.exception.LibroNoDisponibleException;
import com.smartlibrary.exception.RecursoNoEncontradoException;
import com.smartlibrary.model.Estudiante;
import com.smartlibrary.model.Libro;
import com.smartlibrary.model.Prestamo;
import com.smartlibrary.repository.EstudianteRepository;
import com.smartlibrary.repository.LibroRepository;
import com.smartlibrary.repository.PrestamoRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Capa de negocio: coordina el caso de uso "Registrar prestamo" (Controlador GRASP)
@Service
public class PrestamoService {

    private final EstudianteRepository estudianteRepository;
    private final LibroRepository libroRepository;
    private final PrestamoRepository prestamoRepository;

    public PrestamoService(EstudianteRepository estudianteRepository,
                           LibroRepository libroRepository,
                           PrestamoRepository prestamoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.libroRepository = libroRepository;
        this.prestamoRepository = prestamoRepository;
    }

    // Todo o nada: si algo falla, no se cambia el libro ni se crea el prestamo
    @Transactional
    public Prestamo registrarPrestamo(PrestamoRequest request) {
        Estudiante estudiante = estudianteRepository.findById(request.getEstudianteId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el estudiante con id " + request.getEstudianteId()));

        Libro libro = libroRepository.findById(request.getLibroId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el libro con id " + request.getLibroId()));

        // R1: solo un libro disponible puede prestarse
        if (!libro.isDisponible()) {
            throw new LibroNoDisponibleException(
                    "El libro '" + libro.getTitulo() + "' ya esta prestado");
        }

        libro.marcarPrestado(); // R3
        Prestamo prestamo = new Prestamo(estudiante, libro, LocalDate.now()); // R2

        libroRepository.save(libro);
        return prestamoRepository.save(prestamo);
    }

    @Transactional(readOnly = true)
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }
}
