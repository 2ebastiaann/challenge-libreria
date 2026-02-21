package com.sebastian.literatura.service;

import com.sebastian.literatura.dto.LibroDTO;
import com.sebastian.literatura.dto.AutorDTO;
import com.sebastian.literatura.model.Libro;
import com.sebastian.literatura.model.Autor;
import com.sebastian.literatura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorService autorService;

    public LibroService(LibroRepository libroRepository, AutorService autorService) {
        this.libroRepository = libroRepository;
        this.autorService = autorService;
    }

    // Guardar libro directamente, evitando duplicados
    public Libro guardarLibro(Libro libro) {
        if (libro == null || libro.getTitulo() == null || libro.getIdioma() == null || libro.getAutor() == null) {
            return null;
        }

        // Verificar si ya existe un libro con mismo título, idioma y autor
        Optional<Libro> existente = libroRepository.findAll().stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(libro.getTitulo())
                        && l.getIdioma().equalsIgnoreCase(libro.getIdioma())
                        && l.getAutor().getNombre().equalsIgnoreCase(libro.getAutor().getNombre()))
                .findFirst();

        return existente.orElseGet(() -> libroRepository.save(libro));
    }

    // Guardar libro desde DTO, evitando duplicados
    public Libro guardarLibroDesdeDTO(LibroDTO libroDTO) {
        if (libroDTO == null) return null;

        Libro libro = new Libro();
        libro.setTitulo(libroDTO.getTitle());
        libro.setIdioma(libroDTO.getLanguage());
        libro.setNumDescargas(libroDTO.getNumDescargas());

        // Guardar el primer autor del DTO
        AutorDTO autorDTO = libroDTO.getPrimerAutor();
        Autor autor = autorService.guardarAutorDesdeDTO(autorDTO);
        libro.setAutor(autor);

        return guardarLibro(libro); // Usa el método que evita duplicados
    }

    // Listar todos los libros
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    // Buscar libro por título
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Listar libros por idioma
    public List<Libro> listarPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    // Contar libros por idioma
    public Long contarPorIdioma(String idioma) {
        return libroRepository.countByIdioma(idioma);
    }

    // Eliminar libro por ID
    public boolean eliminarLibro(Long id) {
        // Buscar el libro
        Libro libro = libroRepository.findById(id).orElse(null);
        if (libro == null) {
            return false; // No existe
        }

        // Guardar referencia al autor antes de borrar
        Autor autor = libro.getAutor();

        // Borrar el libro
        libroRepository.delete(libro);

        // Revisar si el autor tiene otros libros
        if (autor != null && libroRepository.countByAutorId(autor.getId()) == 0) {
            autorService.eliminarAutor(autor.getId());
        }

        return true;
    }
}