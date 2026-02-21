package com.sebastian.literatura.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;

@Entity
@Table(name = "autores")
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora campos JSON que no mapeamos
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @JsonAlias("birth_year") // Mapea el campo birth_year del JSON a fechaNacimiento
    private Integer fechaNacimiento;

    @JsonAlias("death_year") // Mapea el campo death_year del JSON a fechaFallecimiento
    private Integer fechaFallecimiento;

    // Constructor vacío requerido por JPA
    public Autor() {}

    // Constructor opcional para crear objetos más rápido
    public Autor(String nombre, Integer fechaNacimiento, Integer fechaFallecimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Integer fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public Integer getFechaFallecimiento() { return fechaFallecimiento; }
    public void setFechaFallecimiento(Integer fechaFallecimiento) { this.fechaFallecimiento = fechaFallecimiento; }

    @Override
    public String toString() {
        return "ID: " + id + " - " + nombre + " (" + fechaNacimiento + " - " + fechaFallecimiento + ")";
    }
}