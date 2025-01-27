package com.espe.micro_conductor.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "conductores_vehiculos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"vehiculo_id", "conductor_id"}))
public class ConductorVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehiculo_id", nullable = false)
    private Long vehiculoId;

    @Column(name = "conductor_id", nullable = false)
    private Long conductorId;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Long getConductorId() {
        return conductorId;
    }

    public void setConductorId(Long conductorId) {
        this.conductorId = conductorId;
    }
}
