package com.espe.micro_vehiculos.model.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private String matricula;

    @Temporal(TemporalType.DATE)
    private Date fechaFabricacion;

    private String color;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    // Constructor vacío (obligatorio para JPA)
    public Vehiculo() {}

    // Constructor con parámetros
    public Vehiculo(String marca, String modelo, String matricula, Date fechaFabricacion, String color, Date creadoEn) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.fechaFabricacion = fechaFabricacion;
        this.color = color;
        this.creadoEn = creadoEn;
    }

    // Método para inicializar creadoEn antes de persistir
    @PrePersist
    protected void prePersist() {
        if (this.creadoEn == null) {
            this.creadoEn = new Date();
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }
}
