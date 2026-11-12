package com.smartlibrary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.smartlibrary.repository.PrestamoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

// Prueba los 5 escenarios de la Actividad 6 (+ peticion invalida)
// Datos iniciales: estudiantes 1-2, libros 1-3 (ver DatosIniciales)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PrestamoRepository prestamoRepository;

    private String cuerpo(long estudianteId, long libroId) {
        return "{\"estudianteId\": " + estudianteId + ", \"libroId\": " + libroId + "}";
    }

    @Test
    @DisplayName("1. Prestamo de libro disponible -> 201 Created")
    void prestamoLibroDisponible() throws Exception {
        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo(1, 1)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.estudianteId").value(1))
                .andExpect(jsonPath("$.libroId").value(1))
                .andExpect(jsonPath("$.activo").value(true));

        Assertions.assertEquals(1, prestamoRepository.count());
    }

    @Test
    @DisplayName("2. Libro ya prestado -> 409 Conflict y sin nuevo prestamo")
    void libroYaPrestado() throws Exception {
        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo(1, 2)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo(2, 2)))
                .andDo(print())
                .andExpect(status().isConflict());

        Assertions.assertEquals(1, prestamoRepository.count()); // R4: no se creo otro
    }

    @Test
    @DisplayName("3. Estudiante inexistente -> 404 Not Found")
    void estudianteInexistente() throws Exception {
        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo(99, 1)))
                .andDo(print())
                .andExpect(status().isNotFound());

        Assertions.assertEquals(0, prestamoRepository.count());
    }

    @Test
    @DisplayName("4. Libro inexistente -> 404 Not Found")
    void libroInexistente() throws Exception {
        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo(1, 99)))
                .andDo(print())
                .andExpect(status().isNotFound());

        Assertions.assertEquals(0, prestamoRepository.count());
    }

    @Test
    @DisplayName("5. Consulta de prestamos -> 200 OK con registros")
    void consultaPrestamos() throws Exception {
        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cuerpo(1, 3)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/prestamos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].tituloLibro").value("Spring Boot basico"));
    }

    @Test
    @DisplayName("Extra. Peticion sin libroId -> 400 Bad Request")
    void peticionInvalida() throws Exception {
        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"estudianteId\": 1}"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        Assertions.assertEquals(0, prestamoRepository.count());
    }
}
