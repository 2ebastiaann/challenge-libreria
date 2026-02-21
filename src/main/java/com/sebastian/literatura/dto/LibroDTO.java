package com.sebastian.literatura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDTO {

    @JsonAlias("title")
    private String title;

    @JsonAlias("languages")
    private String[] languages;

    @JsonAlias("download_count")
    private Integer numDescargas;

    @JsonAlias("authors")
    private AutorDTO[] autoresArray;

    // Constructor vacío
    public LibroDTO() {}

    // Getters y setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String[] getLanguages() { return languages; }
    public void setLanguages(String[] languages) { this.languages = languages; }

    public Integer getNumDescargas() { return numDescargas; }
    public void setNumDescargas(Integer numDescargas) { this.numDescargas = numDescargas; }

    public List<AutorDTO> getAutores() {
        return (autoresArray != null) ? Arrays.asList(autoresArray) : List.of();
    }

    public void setAutores(AutorDTO[] autoresArray) { this.autoresArray = autoresArray; }

    // Métodos auxiliares
    public String getLanguage() {
        return (languages != null && languages.length > 0) ? languages[0] : "Desconocido";
    }

    public AutorDTO getPrimerAutor() {
        return (autoresArray != null && autoresArray.length > 0) ? autoresArray[0] : null;
    }

    @Override
    public String toString() {
        return "LibroDTO{" +
                "title='" + title + '\'' +
                ", language='" + getLanguage() + '\'' +
                ", numDescargas=" + numDescargas +
                ", autor=" + (getPrimerAutor() != null ? getPrimerAutor().getNombre() : "Desconocido") +
                '}';
    }
}