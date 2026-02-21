package com.sebastian.literatura.service;

import com.sebastian.literatura.dto.AutorDTO;
import com.sebastian.literatura.model.Autor;
import com.sebastian.literatura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Guardar entidad Autor directamente, evitando duplicados por nombre
    public Autor guardarAutor(Autor autor) {
        if (autor == null || autor.getNombre() == null) return null;

        Autor existente = autorRepository.findByNombreIgnoreCase(autor.getNombre());
        return (existente != null) ? existente : autorRepository.save(autor);
    }

    // Guardar desde DTO, evitando duplicados
    public Autor guardarAutorDesdeDTO(AutorDTO autorDTO) {
        if (autorDTO == null || autorDTO.getNombre() == null) return null;

        Autor existente = autorRepository.findByNombreIgnoreCase(autorDTO.getNombre());
        if (existente != null) {
            return existente;
        }

        Autor autor = new Autor();
        autor.setNombre(autorDTO.getNombre());
        autor.setFechaNacimiento(autorDTO.getFechaNacimiento());
        autor.setFechaFallecimiento(autorDTO.getFechaFallecimiento());

        return autorRepository.save(autor);
    }

    // Listar todos los autores
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    // Listar autores vivos en un año específico
    public List<Autor> listarAutoresVivosEnAño(int año) {
        return autorRepository.findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThanEqual(año, año);
    }

    // Eliminar autor por ID
    public boolean eliminarAutor(Long id) {
        if (!autorRepository.existsById(id)) {
            return false; // No existe
        }
        autorRepository.deleteById(id);
        return true;
    }
}