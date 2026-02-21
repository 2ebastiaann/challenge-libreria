package com.sebastian.literatura;

import com.sebastian.literatura.client.GutendexClient;
import com.sebastian.literatura.model.Autor;
import com.sebastian.literatura.model.Libro;
import com.sebastian.literatura.service.AutorService;
import com.sebastian.literatura.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	private final LibroService libroService;
	private final AutorService autorService;
	private final GutendexClient gutendexClient;

	public LiteraturaApplication(LibroService libroService, AutorService autorService, GutendexClient gutendexClient) {
		this.libroService = libroService;
		this.autorService = autorService;
		this.gutendexClient = gutendexClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;

		while (!salir) {
			System.out.println("\n=== MENÚ LITERALURA ===");
			System.out.println("1. Buscar libro por título");
			System.out.println("2. Listar libros registrados");
			System.out.println("3. Listar autores registrados");
			System.out.println("4. Listar autores vivos en determinado año");
			System.out.println("5. Listar libros por idioma");
			System.out.println("6. Eliminar libro por ID");
			System.out.println("7. Salir");
			System.out.print("Seleccione una opción: ");

			String opcion = scanner.nextLine();

			switch (opcion) {
				case "1":
					System.out.print("Ingrese el título del libro: ");
					String titulo = scanner.nextLine().trim();
					if (titulo.isEmpty()) {
						System.out.println("Título inválido. Intente de nuevo.");
						break;
					}
					try {
						Libro libro = gutendexClient.buscarLibroPorTitulo(titulo);
						if (libro != null) {
							System.out.println("\nLibro encontrado y guardado:");
							System.out.println(libro);
						} else {
							System.out.println("No se encontró ningún libro con ese título.");
						}
					} catch (IOException | InterruptedException e) {
						System.out.println("Error al consultar la API: " + e.getMessage());
					} catch (Exception e) {
						System.out.println("Ocurrió un error inesperado: " + e.getMessage());
					}
					break;

				case "2":
					try {
						List<Libro> libros = libroService.listarLibros();
						if (libros.isEmpty()) {
							System.out.println("No hay libros registrados.");
						} else {
							System.out.println("\nLibros registrados:");
							libros.forEach(System.out::println);
						}
					} catch (Exception e) {
						System.out.println("Error al listar libros: " + e.getMessage());
					}
					break;

				case "3":
					try {
						List<Autor> autores = autorService.listarAutores();
						if (autores.isEmpty()) {
							System.out.println("No hay autores registrados.");
						} else {
							System.out.println("\nAutores registrados:");
							autores.forEach(System.out::println);
						}
					} catch (Exception e) {
						System.out.println("Error al listar autores: " + e.getMessage());
					}
					break;

				case "4":
					System.out.print("Ingrese el año para filtrar autores vivos: ");
					try {
						String input = scanner.nextLine().trim();
						int anio = Integer.parseInt(input);
						List<Autor> autoresVivos = autorService.listarAutoresVivosEnAño(anio);
						if (autoresVivos.isEmpty()) {
							System.out.println("No se encontraron autores vivos en el año " + anio);
						} else {
							System.out.println("\nAutores vivos en " + anio + ":");
							autoresVivos.forEach(System.out::println);
						}
					} catch (NumberFormatException e) {
						System.out.println("Año inválido. Debe ingresar un número.");
					} catch (Exception e) {
						System.out.println("Error al listar autores: " + e.getMessage());
					}
					break;

				case "5":
					System.out.print("Ingrese el idioma para filtrar libros: ");
					String idioma = scanner.nextLine().trim();
					if (idioma.isEmpty()) {
						System.out.println("Idioma inválido. Intente de nuevo.");
						break;
					}
					try {
						List<Libro> librosPorIdioma = libroService.listarPorIdioma(idioma);
						if (librosPorIdioma.isEmpty()) {
							System.out.println("No se encontraron libros en el idioma " + idioma);
						} else {
							System.out.println("\nLibros en " + idioma + ":");
							librosPorIdioma.forEach(System.out::println);
						}
					} catch (Exception e) {
						System.out.println("Error al listar libros por idioma: " + e.getMessage());
					}
					break;

				case "6":
					System.out.print("Ingrese el ID del libro a eliminar: ");
					try {
						Long libroId = Long.parseLong(scanner.nextLine());
						boolean eliminado = libroService.eliminarLibro(libroId);
						if (eliminado) {
							System.out.println("Libro eliminado correctamente.");
						} else {
							System.out.println("No se encontró ningún libro con ese ID.");
						}
					} catch (NumberFormatException e) {
						System.out.println("ID inválido. Ingrese un número.");
					}
					break;

				case "7":
					System.out.println("Saliendo del programa...");
					salir = true;
					break;



				default:
					System.out.println("Opción inválida. Intente de nuevo.");
			}
		}

		scanner.close();
	}
}