package com.espe.micro_conductor.clients;

import com.espe.micro_conductor.model.entity.Vehiculo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "micro-vehiculo", url = "micro-vehiculo:8002/api/vehiculos")
public interface VehiculoClient {

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarVehiculos();

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerVehiculo(@PathVariable Long id);

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearVehiculo(@RequestBody Vehiculo vehiculo);

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculo);

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarVehiculo(@PathVariable Long id);
}
