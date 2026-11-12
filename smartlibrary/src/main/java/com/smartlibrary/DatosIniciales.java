package com.smartlibrary;

import com.smartlibrary.model.Estudiante;
import com.smartlibrary.model.Libro;
import com.smartlibrary.repository.EstudianteRepository;
import com.smartlibrary.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Datos de prueba: estudiantes 1-2 y libros 1-3 (todos disponibles al arrancar)
@Component
public class DatosIniciales implements CommandLineRunner {

    private final EstudianteRepository estudianteRepository;
    private final LibroRepository libroRepository;

    public DatosIniciales(EstudianteRepository estudianteRepository, LibroRepository libroRepository) {
        this.estudianteRepository = estudianteRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    public void run(String... args) {
        estudianteRepository.save(new Estudiante("Ana Torres", "ana@correo.edu"));
        estudianteRepository.save(new Estudiante("Luis Gomez", "luis@correo.edu"));
        libroRepository.save(new Libro("Diseno de Software"));
        libroRepository.save(new Libro("Patrones GRASP"));
        libroRepository.save(new Libro("Spring Boot basico"));
    }
}
