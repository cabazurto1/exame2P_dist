package com.espe.micro_conductor.controllers;

import com.espe.micro_conductor.model.entity.ConductorVehiculo;
import com.espe.micro_conductor.repositories.ConductorVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conductor-vehiculo")
@CrossOrigin(origins = "http://localhost:3000")
public class ConductorVehiculoController {

    @Autowired
    private ConductorVehiculoRepository conductorVehiculoRepository;

    @PostMapping
    public ResponseEntity<ConductorVehiculo> asignarVehiculoAConductor(@RequestBody ConductorVehiculo conductorVehiculo) {
        ConductorVehiculo nuevaRelacion = conductorVehiculoRepository.save(conductorVehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRelacion);
    }

    @GetMapping
    public ResponseEntity<List<ConductorVehiculo>> obtenerRelaciones() {
        List<ConductorVehiculo> relaciones = conductorVehiculoRepository.findAll();
        return ResponseEntity.ok(relaciones);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRelacion(@PathVariable Long id) {
        conductorVehiculoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConductorVehiculo> actualizarRelacion(
            @PathVariable Long id,
            @RequestBody ConductorVehiculo detallesActualizados) {
        return conductorVehiculoRepository.findById(id)
                .map(relacionExistente -> {
                    relacionExistente.setVehiculoId(detallesActualizados.getVehiculoId());
                    relacionExistente.setConductorId(detallesActualizados.getConductorId());
                    ConductorVehiculo relacionActualizada = conductorVehiculoRepository.save(relacionExistente);
                    return ResponseEntity.ok(relacionActualizada);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/vehiculo/{vehiculoId}")
    public ResponseEntity<List<ConductorVehiculo>> listarConductoresPorVehiculo(@PathVariable Long vehiculoId) {
        List<ConductorVehiculo> conductoresPorVehiculo = conductorVehiculoRepository.findByVehiculoId(vehiculoId);
        if (conductoresPorVehiculo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(conductoresPorVehiculo);
    }
    @GetMapping("/conductor/{conductorId}")
        public ResponseEntity<List<ConductorVehiculo>> listarVehiculosPorConductor(@PathVariable Long conductorId) {
            List<ConductorVehiculo> vehiculosPorConductor = conductorVehiculoRepository.findByConductorId(conductorId);
            if (vehiculosPorConductor.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(vehiculosPorConductor);
        }


}
