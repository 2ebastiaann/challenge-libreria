package com.sebastian.literatura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutorDTO {

    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private Integer fechaNacimiento;

    @JsonAlias("death_year")
    private Integer fechaFallecimiento;

    // Constructor vacío
    public AutorDTO() {}

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Integer fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public Integer getFechaFallecimiento() { return fechaFallecimiento; }
    public void setFechaFallecimiento(Integer fechaFallecimiento) { this.fechaFallecimiento = fechaFallecimiento; }

    @Override
    public String toString() {
        return nombre + " (" + fechaNacimiento + " - " + fechaFallecimiento + ")";
    }
}