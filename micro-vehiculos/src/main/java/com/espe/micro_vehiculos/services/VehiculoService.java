package com.espe.micro_vehiculos.services;

import com.espe.micro_vehiculos.model.entity.Vehiculo;

import java.util.Date;
import java.util.List;

public interface VehiculoService {
    // Método para listar todos los vehículos
    List<Vehiculo> listarTodos();

    // Método para guardar un vehículo
    Vehiculo guardarVehiculo(Vehiculo vehiculo);

    // Método para obtener un vehículo por su ID
    Vehiculo obtenerPorId(Long id);

    // Método para eliminar un vehículo por su ID
    void eliminarPorId(Long id);

    // Métodos adicionales para búsquedas personalizadas
    Vehiculo buscarPorMatricula(String matricula);

    List<Vehiculo> buscarPorMarca(String marca);

    List<Vehiculo> buscarPorModelo(String modelo);

    List<Vehiculo> buscarPorFechaFabricacionPosterior(Date fecha);

    List<Vehiculo> buscarPorColorContiene(String texto);
}
