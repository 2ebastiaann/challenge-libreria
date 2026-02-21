package com.sebastian.literatura.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "libros")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String idioma;

    private Integer numDescargas;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    // Constructor vacío
    public Libro() {}

    // Constructor opcional
    public Libro(String titulo, String idioma, Integer numDescargas, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.numDescargas = numDescargas;
        this.autor = autor;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public Integer getNumDescargas() { return numDescargas; }
    public void setNumDescargas(Integer numDescargas) { this.numDescargas = numDescargas; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "ID: " + id + " - " + titulo + " (" + idioma + ") - Descargas: " + numDescargas +
                " - Autor: " + (autor != null ? autor.getNombre() : "Desconocido");
    }
}