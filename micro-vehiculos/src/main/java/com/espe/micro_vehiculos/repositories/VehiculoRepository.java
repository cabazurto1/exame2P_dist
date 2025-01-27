package com.espe.micro_vehiculos.repositories;

import com.espe.micro_vehiculos.model.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    // Buscar vehículo por matrícula
    Vehiculo findByMatricula(String matricula);

    // Buscar vehículos por marca
    List<Vehiculo> findByMarca(String marca);

    // Buscar vehículos por modelo
    List<Vehiculo> findByModelo(String modelo);

    // Buscar vehículos fabricados después de una fecha específica
    List<Vehiculo> findByFechaFabricacionAfter(Date fecha);

    // Buscar vehículos cuyo color contenga un texto específico
    List<Vehiculo> findByColorContaining(String texto);
}
