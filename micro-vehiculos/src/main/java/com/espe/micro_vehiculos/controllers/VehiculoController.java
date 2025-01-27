package com.espe.micro_vehiculos.controllers;

import com.espe.micro_vehiculos.model.entity.Vehiculo;
import com.espe.micro_vehiculos.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "http://localhost:3000")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.listarTodos();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Vehículos obtenidos exitosamente.");
        response.put("data", vehiculos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerVehiculo(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Vehiculo vehiculo = vehiculoService.obtenerPorId(id);
            response.put("message", "Vehículo encontrado exitosamente.");
            response.put("data", vehiculo);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Vehículo no encontrado con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        Vehiculo vehiculoCreado = vehiculoService.guardarVehiculo(vehiculo);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Vehículo creado exitosamente.");
        response.put("data", vehiculoCreado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        Map<String, Object> response = new HashMap<>();
        try {
            Vehiculo vehiculoExistente = vehiculoService.obtenerPorId(id);
            vehiculoExistente.setMarca(vehiculo.getMarca());
            vehiculoExistente.setModelo(vehiculo.getModelo());
            vehiculoExistente.setMatricula(vehiculo.getMatricula());
            vehiculoExistente.setFechaFabricacion(vehiculo.getFechaFabricacion());
            vehiculoExistente.setColor(vehiculo.getColor());
            Vehiculo vehiculoActualizado = vehiculoService.guardarVehiculo(vehiculoExistente);
            response.put("message", "Vehículo actualizado exitosamente.");
            response.put("data", vehiculoActualizado);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Vehículo no encontrado con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarVehiculo(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            vehiculoService.eliminarPorId(id);
            response.put("message", "Vehículo eliminado exitosamente.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Vehículo no encontrado con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
