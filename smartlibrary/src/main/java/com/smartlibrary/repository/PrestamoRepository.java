package com.smartlibrary.repository;

import com.smartlibrary.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data genera la implementacion (findById, findAll, save...)
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
