package com.sebastian.literatura.repository;

import com.sebastian.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autor por nombre ignorando mayúsculas/minúsculas
    Autor findByNombreIgnoreCase(String nombre);

    // Lista de autores vivos en un año específico
    List<Autor> findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThanEqual(Integer añoNacimiento, Integer añoFallecimiento);

    // Lista de todos los autores (ya lo tienes por defecto con findAll())
}