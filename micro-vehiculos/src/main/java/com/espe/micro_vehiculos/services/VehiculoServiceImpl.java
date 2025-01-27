package com.espe.micro_vehiculos.services;

import com.espe.micro_vehiculos.model.entity.Vehiculo;
import com.espe.micro_vehiculos.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> listarTodos() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo obtenerPorId(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));
    }

    @Override
    public void eliminarPorId(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new RuntimeException("Vehículo no encontrado con ID: " + id);
        }
        vehiculoRepository.deleteById(id);
    }

    @Override
    public Vehiculo buscarPorMatricula(String matricula) {
        return vehiculoRepository.findByMatricula(matricula);
    }

    @Override
    public List<Vehiculo> buscarPorMarca(String marca) {
        return vehiculoRepository.findByMarca(marca);
    }

    @Override
    public List<Vehiculo> buscarPorModelo(String modelo) {
        return vehiculoRepository.findByModelo(modelo);
    }

    @Override
    public List<Vehiculo> buscarPorFechaFabricacionPosterior(Date fecha) {
        return vehiculoRepository.findByFechaFabricacionAfter(fecha);
    }

    @Override
    public List<Vehiculo> buscarPorColorContiene(String texto) {
        return vehiculoRepository.findByColorContaining(texto);
    }
}
