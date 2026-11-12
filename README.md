# SmartLibrary · Taller práctico Bloque 7

Rediseño del caso de uso **«Registrar préstamo»** de SmartLibrary: del modelo de clases inicial
(`GestorPrestamos`, `Estudiante`, `Libro`, `Prestamo`) a una API REST en tres capas
(Controller → Service → Repository) con Spring Boot.

**Integrantes:** Kevin Basante y Arley Riascos
**Asignatura:** Diseño de Software · Cuarto semestre

## Contenido del repositorio

| Carpeta / archivo | Qué contiene |
|---|---|
| `smartlibrary/` | Proyecto Spring Boot 3 (Java 21, Maven, Spring Web, Spring Data JPA, Validation, H2) |
| `docs/Taller_Bloque7_SmartLibrary.docx` | Documento del taller con las 7 actividades |
| `docs/diagramas/` | Diagramas en Visual Paradigm (`.vpp`) y exportados en PNG: modelo inicial anotado, clases por capas y secuencia |
| `docs/evidencias/` | Resultado de las pruebas (`mvn test`) y de las peticiones HTTP |

## Cómo ejecutar

```bash
cd smartlibrary
mvn spring-boot:run   # API en http://localhost:8080
mvn test              # 6 pruebas, 0 fallos
```

Endpoints: `POST /api/prestamos` con `{"estudianteId": 1, "libroId": 1}` y `GET /api/prestamos`.
Más detalles en [`smartlibrary/README.md`](smartlibrary/README.md).
