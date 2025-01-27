package com.espe.micro_conductor.services;

import com.espe.micro_conductor.model.entity.Conductor;
import com.espe.micro_conductor.model.entity.Vehiculo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConductorService {
    // Método para listar todos los conductores
    List<Conductor> listarTodos();

    // Método para guardar un conductor
    Conductor guardarConductor(Conductor conductor);

    // Método para obtener un conductor por su ID
    Conductor obtenerPorId(Long id);

    // Método para eliminar un conductor por su ID
    void eliminarPorId(Long id);

    // Métodos adicionales para gestionar vehículos asociados
    Optional<Vehiculo> addVehiculo(Vehiculo vehiculo, Long id);

    boolean removerVehiculo(Vehiculo vehiculo, Long id);

    // Métodos adicionales para búsquedas personalizadas
    List<Conductor> buscarPorNombre(String nombre);

    Conductor buscarPorLicencia(String licencia);

    List<Conductor> buscarPorFechaCreacionPosterior(Date fecha);

    List<Conductor> buscarPorNombreContiene(String texto);
}
