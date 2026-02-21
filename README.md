# Literatura API

<img width="960" height="647" alt="image" src="https://github.com/user-attachments/assets/0d2eb73a-b6fe-4bd1-828e-5b9efa99df50" />


---

## Insignias

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Status](https://img.shields.io/badge/Status-Activo-success)

---

## Índice

1. Descripción del Proyecto  
2. Estado del proyecto  
3. Demostración de funciones  
4. Acceso al Proyecto  
5. Tecnologías utilizadas  
6. Personas Desarrolladoras del Proyecto  
---

## Descripción del Proyecto

Literatura API es una aplicación backend desarrollada con Spring Boot que permite gestionar una base de datos de libros y autores.

La aplicación permite:

- Buscar libros por título
- Registrar libros en base de datos
- Evitar libros duplicados
- Listar libros registrados
- Eliminar libros por ID
- Asociar libros con sus autores
- Eliminar autores automáticamente cuando ya no tienen libros asociados

El sistema incluye manejo de errores para evitar fallos cuando el usuario introduce datos inválidos o intenta registrar información duplicada.

---

## Estado del proyecto

Proyecto funcional.

Actualmente incluye:

- Persistencia en base de datos
- Validación para evitar duplicados
- Eliminación de libros por ID
- Visualización del ID en listados

Posibles mejoras futuras:

- Paginación
- Documentación con Swagger
- API REST pública
- Tests automatizados

---

## Demostración de funciones

### Registrar libro

- Busca el libro desde una API externa.
- Verifica si ya existe en la base de datos.
- Si no existe, lo guarda junto con su autor.

### Evitar duplicados

El sistema valida que no exista un libro con el mismo:

- Título  
- Idioma  
- Autor  

Antes de registrarlo.

### Listar libros

Muestra:

- ID
- Título
- Autor
- Idioma
- Número de descargas

### Eliminar libro por ID

Permite borrar un libro específico usando su ID.
Si el autor queda sin libros asociados, también puede eliminarse automáticamente según la configuración implementada.

---

## Acceso al Proyecto

### Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/literatura-api.git
cd literatura-api
```

## Tecnologías utilizadas

- Java 17  
- Spring Boot 3  
- Spring Data JPA  
- Hibernate  
- PostgreSQL  
- Maven  
- API externa de libros (Gutendex API)  
- Jackson (para mapeo de JSON a DTOs)  

---

## Personas Desarrolladoras del Proyecto

**Sebastián Carmona**

Proyecto desarrollado como práctica para reforzar conocimientos en:

- Desarrollo backend con Spring Boot
- Consumo de APIs externas
- Manejo de DTOs
- Persistencia con JPA
- Diseño de repositorios
- Validaciones y lógica de negocio
- Relaciones entre entidades (Libro - Autor)

---
