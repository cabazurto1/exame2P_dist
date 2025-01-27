package com.espe.micro_conductor.controllers;

import com.espe.micro_conductor.model.entity.Conductor;
import com.espe.micro_conductor.model.entity.Vehiculo;
import com.espe.micro_conductor.services.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/conductores")
@CrossOrigin(origins = "http://localhost:3000") // Habilitar CORS solo para este origen
public class ConductorController {

    @Autowired
    private ConductorService conductorService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarConductores() {
        List<Conductor> conductores = conductorService.listarTodos();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Conductores obtenidos exitosamente.");
        response.put("data", conductores);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerConductor(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Conductor conductor = conductorService.obtenerPorId(id);
            response.put("message", "Conductor encontrado exitosamente.");
            response.put("data", conductor);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Conductor no encontrado con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearConductor(@RequestBody Conductor conductor) {
        Conductor conductorCreado = conductorService.guardarConductor(conductor);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Conductor creado exitosamente.");
        response.put("data", conductorCreado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarConductor(@PathVariable Long id, @RequestBody Conductor conductor) {
        Map<String, Object> response = new HashMap<>();
        try {
            Conductor conductorExistente = conductorService.obtenerPorId(id);
            conductorExistente.setNombre(conductor.getNombre());
            conductorExistente.setLicencia(conductor.getLicencia());
            Conductor conductorActualizado = conductorService.guardarConductor(conductorExistente);
            response.put("message", "Conductor actualizado exitosamente.");
            response.put("data", conductorActualizado);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Conductor no encontrado con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarConductor(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            conductorService.eliminarPorId(id);
            response.put("message", "Conductor eliminado exitosamente.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Conductor no encontrado con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{conductorId}/asignar-vehiculo")
    public ResponseEntity<Map<String, Object>> asignarVehiculo(@PathVariable Long conductorId, @RequestBody Vehiculo vehiculo) {
        Optional<Vehiculo> vehiculoAsignado = conductorService.addVehiculo(vehiculo, conductorId);
        if (vehiculoAsignado.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Vehículo asignado exitosamente.");
            response.put("data", vehiculoAsignado.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "Conductor no encontrado con ID: " + conductorId));
        }
    }

    @DeleteMapping("/{conductorId}/remover-vehiculo")
    public ResponseEntity<Map<String, Object>> removerVehiculo(@PathVariable Long conductorId, @RequestBody Vehiculo vehiculo) {
        boolean removido = conductorService.removerVehiculo(vehiculo, conductorId);
        if (removido) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Vehículo removido exitosamente."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "Conductor o vehículo no encontrado."));
        }
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<Map<String, Object>> buscarPorNombre(@PathVariable String nombre) {
        List<Conductor> conductores = conductorService.buscarPorNombre(nombre);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Conductores encontrados por nombre.");
        response.put("data", conductores);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar/licencia/{licencia}")
    public ResponseEntity<Map<String, Object>> buscarPorLicencia(@PathVariable String licencia) {
        Conductor conductor = conductorService.buscarPorLicencia(licencia);
        Map<String, Object> response = new HashMap<>();
        if (conductor != null) {
            response.put("message", "Conductor encontrado por licencia.");
            response.put("data", conductor);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Conductor no encontrado con licencia: " + licencia);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
