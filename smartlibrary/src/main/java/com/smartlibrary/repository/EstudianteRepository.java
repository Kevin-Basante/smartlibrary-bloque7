package com.smartlibrary.repository;

import com.smartlibrary.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data genera la implementacion (findById, findAll, save...)
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
