package com.espe.micro_conductor.repositories;

import com.espe.micro_conductor.model.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {
    // Buscar conductores por nombre
    List<Conductor> findByNombre(String nombre);

    // Buscar conductores por licencia
    Conductor findByLicencia(String licencia);

    // Buscar conductores creados después de una fecha específica
    List<Conductor> findByCreadoEnAfter(Date fecha);

    // Buscar conductores cuyo nombre contenga un texto específico
    List<Conductor> findByNombreContaining(String texto);
}
