package com.example.disciplinas.controller;

import com.example.disciplinas.model.Disciplina;
import com.example.disciplinas.repository.DisciplinaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DisciplinaController.class)
@DisplayName("Testes do Controller de Disciplinas")
class DisciplinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisciplinaRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private Disciplina disciplina1;
    private Disciplina disciplina2;
    private Disciplina novaDisciplina;

    @BeforeEach
    void setUp() {
        disciplina1 = new Disciplina(1L, "Desenvolvimento de Aplicações Modernas", 80, "Prof. João Silva", "Descrição 1");
        disciplina2 = new Disciplina(2L, "Banco de Dados Avançado", 60, "Prof. Maria Santos", "Descrição 2");
        novaDisciplina = new Disciplina(null, "Nova Disciplina", 40, "Prof. Teste", "Descrição teste");
    }

    @Test
    @DisplayName("GET /api/disciplinas - Deve listar todas as disciplinas com sucesso")
    void deveListarTodasDisciplinas() throws Exception {
        when(repository.findAll()).thenReturn(Arrays.asList(disciplina1, disciplina2));

        mockMvc.perform(get("/api/disciplinas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Desenvolvimento de Aplicações Modernas"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Banco de Dados Avançado"));
    }

    @Test
    @DisplayName("GET /api/disciplinas/{id} - Deve buscar disciplina existente por ID")
    void deveBuscarDisciplinaPorIdExistente() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.of(disciplina1));

        mockMvc.perform(get("/api/disciplinas/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Desenvolvimento de Aplicações Modernas"))
                .andExpect(jsonPath("$.cargaHoraria").value(80))
                .andExpect(jsonPath("$.professor").value("Prof. João Silva"));
    }

    @Test
    @DisplayName("GET /api/disciplinas/{id} - Deve retornar 404 quando disciplina não existe")
    void deveRetornar404QuandoDisciplinaNaoExiste() throws Exception {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/disciplinas/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/disciplinas - Deve criar nova disciplina com sucesso")
    void deveCriarNovaDisciplina() throws Exception {
        Disciplina disciplinaSalva = new Disciplina(3L, "Nova Disciplina", 40, "Prof. Teste", "Descrição teste");
        when(repository.save(any(Disciplina.class))).thenReturn(disciplinaSalva);

        mockMvc.perform(post("/api/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novaDisciplina)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.nome").value("Nova Disciplina"))
                .andExpect(jsonPath("$.cargaHoraria").value(40))
                .andExpect(jsonPath("$.professor").value("Prof. Teste"));
    }

    @Test
    @DisplayName("POST /api/disciplinas - Deve retornar 400 quando dados inválidos")
    void deveRetornar400QuandoDadosInvalidos() throws Exception {
        Disciplina disciplinaInvalida = new Disciplina();
        disciplinaInvalida.setNome(""); // Nome vazio

        mockMvc.perform(post("/api/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplinaInvalida)))
                .andExpect(status().isBadRequest());
    }
}

