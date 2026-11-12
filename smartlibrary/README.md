# SmartLibrary · Registrar préstamo (Taller bloque 7)

API REST en tres capas (Controller → Service → Repository) con Spring Boot 3, Java 21, Maven,
Spring Web, Spring Data JPA, Validation y H2.

## Paquetes

| Paquete | Clases | Capa |
|---|---|---|
| `controller` | `PrestamoController`, `ManejadorErrores` | Presentación (HTTP) |
| `dto` | `PrestamoRequest`, `PrestamoResponse` | Presentación (formato de entrada/salida) |
| `service` | `PrestamoService` | Negocio (caso de uso, transacción) |
| `exception` | `RecursoNoEncontradoException`, `LibroNoDisponibleException` | Negocio (errores del caso de uso) |
| `repository` | `EstudianteRepository`, `LibroRepository`, `PrestamoRepository` | Datos |
| `model` | `Estudiante`, `Libro`, `Prestamo` | Modelo de dominio (no es una capa) |

## Cómo ejecutar

```bash
mvn spring-boot:run
```

Al arrancar se cargan datos de prueba (`DatosIniciales`): estudiantes **1** y **2**, libros **1**, **2** y **3**, todos disponibles.
Consola de H2: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:smartlibrary`, usuario `sa`, sin clave).

## Endpoints

| Método y ruta | Cuerpo | Éxito | Errores |
|---|---|---|---|
| `POST /api/prestamos` | `{"estudianteId": 1, "libroId": 1}` | 201 Created + préstamo | 400 (falta un id), 404 (estudiante o libro no existe), 409 (libro no disponible) |
| `GET /api/prestamos` | — | 200 OK + lista | — |

## Pruebas

```bash
mvn test
```

`PrestamoControllerTest` cubre los 5 escenarios del taller (más una petición inválida → 400).
Resultado: `Tests run: 6, Failures: 0, Errors: 0`. Ver la carpeta `../docs/evidencias/`.

| Escenario | Esperado | Prueba |
|---|---|---|
| Préstamo de libro disponible | 201 Created y préstamo registrado | `prestamoLibroDisponible` |
| Libro ya prestado | 409 Conflict; sin nuevo préstamo | `libroYaPrestado` |
| Estudiante inexistente | 404 Not Found | `estudianteInexistente` |
| Libro inexistente | 404 Not Found | `libroInexistente` |
| Consulta de préstamos | 200 OK con registros | `consultaPrestamos` |
