package com.smartlibrary.repository;

import com.smartlibrary.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data genera la implementacion (findById, findAll, save...)
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
