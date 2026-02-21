package com.sebastian.literatura.repository;

import com.sebastian.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libros por título (exacto o parcial)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // Listar todos los libros en un idioma específico
    List<Libro> findByIdioma(String idioma);

    // Contar cantidad de libros por idioma
    Long countByIdioma(String idioma);

    // Contar libros de un autor específico
    Long countByAutorId(Long autorId);
}