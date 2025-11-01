package com.example.disciplinas.repository;

import com.example.disciplinas.model.Disciplina;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Repositório de Disciplinas")
class DisciplinaRepositoryTest {

    private DisciplinaRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DisciplinaRepository();
    }

    @Test
    @DisplayName("Deve inicializar com duas disciplinas")
    void deveInicializarComDuasDisciplinas() {
        var disciplinas = repository.findAll();
        
        assertEquals(2, disciplinas.size());
        assertEquals("Desenvolvimento de Aplicações Modernas", disciplinas.get(0).getNome());
        assertEquals("Banco de Dados Avançado", disciplinas.get(1).getNome());
    }

    @Test
    @DisplayName("Deve buscar disciplina existente por ID")
    void deveBuscarDisciplinaPorId() {
        var disciplina = repository.findById(1L);
        
        assertTrue(disciplina.isPresent());
        assertEquals(1L, disciplina.get().getId());
        assertEquals("Desenvolvimento de Aplicações Modernas", disciplina.get().getNome());
    }

    @Test
    @DisplayName("Deve retornar Optional vazio para ID inexistente")
    void deveRetornarVazioParaIdInexistente() {
        var disciplina = repository.findById(999L);
        
        assertTrue(disciplina.isEmpty());
    }

    @Test
    @DisplayName("Deve salvar nova disciplina com ID gerado automaticamente")
    void deveSalvarNovaDisciplina() {
        Disciplina nova = new Disciplina(null, "Programação Web", 60, "Prof. Carlos", "Descrição");
        
        var disciplinaSalva = repository.save(nova);
        
        assertNotNull(disciplinaSalva.getId());
        assertEquals("Programação Web", disciplinaSalva.getNome());
        assertTrue(repository.findById(disciplinaSalva.getId()).isPresent());
    }

    @Test
    @DisplayName("Deve listar todas as disciplinas")
    void deveListarTodasDisciplinas() {
        repository.save(new Disciplina(null, "Disciplina Teste", 40, "Prof. Teste", "Descrição"));
        
        var disciplinas = repository.findAll();
        
        assertTrue(disciplinas.size() >= 3);
    }
}

