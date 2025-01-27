package com.espe.micro_conductor.services;

import com.espe.micro_conductor.model.entity.Conductor;
import com.espe.micro_conductor.model.entity.Vehiculo;
import com.espe.micro_conductor.repositories.ConductorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConductorServiceImpl implements ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Conductor> listarTodos() {
        return conductorRepository.findAll();
    }

    @Override
    public Conductor guardarConductor(Conductor conductor) {
        return conductorRepository.save(conductor);
    }

    @Override
    public Conductor obtenerPorId(Long id) {
        return conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado con ID: " + id));
    }

    @Override
    public void eliminarPorId(Long id) {
        conductorRepository.deleteById(id);
    }

    @Override
    public Optional<Vehiculo> addVehiculo(Vehiculo vehiculo, Long id) {
        Optional<Conductor> optionalConductor = conductorRepository.findById(id);
        if (optionalConductor.isPresent()) {
            Conductor conductor = optionalConductor.get();
            conductor.addVehiculo(vehiculo);
            conductorRepository.save(conductor);
            return Optional.of(vehiculo);
        }
        return Optional.empty();
    }

    @Override
    public boolean removerVehiculo(Vehiculo vehiculo, Long id) {
        Optional<Conductor> optionalConductor = conductorRepository.findById(id);
        if (optionalConductor.isPresent()) {
            Conductor conductor = optionalConductor.get();
            boolean removed = conductor.getVehiculos().removeIf(v -> v.getId().equals(vehiculo.getId()));
            if (removed) {
                conductorRepository.save(conductor);
            }
            return removed;
        }
        return false;
    }

    @Override
    public List<Conductor> buscarPorNombre(String nombre) {
        return conductorRepository.findByNombre(nombre);
    }

    @Override
    public Conductor buscarPorLicencia(String licencia) {
        return conductorRepository.findByLicencia(licencia);
    }

    @Override
    public List<Conductor> buscarPorFechaCreacionPosterior(Date fecha) {
        return conductorRepository.findByCreadoEnAfter(fecha);
    }

    @Override
    public List<Conductor> buscarPorNombreContiene(String texto) {
        return conductorRepository.findByNombreContaining(texto);
    }
}
