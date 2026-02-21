package com.sebastian.literatura.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebastian.literatura.model.Autor;
import com.sebastian.literatura.model.Libro;
import com.sebastian.literatura.service.AutorService;
import com.sebastian.literatura.service.LibroService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class GutendexClient {

    private final LibroService libroService;
    private final AutorService autorService;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private static final String BASE_URL = "https://gutendex.com/books/";

    public GutendexClient(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Busca un libro por título en Gutendex, toma el primer resultado,
     * guarda el autor y el libro en la base de datos y lo retorna.
     */
    public Libro buscarLibroPorTitulo(String titulo) throws IOException, InterruptedException {
        // Construir la URL de consulta
        String url = BASE_URL + "?search=" + titulo.replace(" ", "%20");

        // Crear solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Enviar solicitud
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Parsear JSON
        JsonNode root = objectMapper.readTree(response.body());
        JsonNode results = root.path("results");

        if (results.isArray() && results.size() > 0) {
            JsonNode libroNode = results.get(0);

            // Extraer autor
            JsonNode autorNode = libroNode.path("authors").get(0);
            Autor autor = new Autor();
            autor.setNombre(autorNode.path("name").asText());
            autor.setFechaNacimiento(autorNode.path("birth_year").asInt(0)); // 0 si es nulo
            autor.setFechaFallecimiento(autorNode.path("death_year").asInt(0));

            // Guardar autor en DB
            autor = autorService.guardarAutor(autor);

            // Crear libro
            Libro libro = new Libro();
            libro.setTitulo(libroNode.path("title").asText());
            libro.setIdioma(libroNode.path("languages").get(0).asText()); // Tomamos solo el primer idioma
            libro.setNumDescargas(libroNode.path("download_count").asInt());
            libro.setAutor(autor);

            // Guardar libro en DB
            libro = libroService.guardarLibro(libro);

            return libro;
        }

        return null; // Si no se encontró ningún libro
    }
}