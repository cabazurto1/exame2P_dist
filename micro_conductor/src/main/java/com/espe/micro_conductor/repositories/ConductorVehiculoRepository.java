package com.espe.micro_conductor.repositories;

import com.espe.micro_conductor.model.entity.ConductorVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ConductorVehiculoRepository extends JpaRepository<ConductorVehiculo, Long> {
    List<ConductorVehiculo> findByVehiculoId(Long vehiculoId);
    List<ConductorVehiculo> findByConductorId(Long conductorId);
}
