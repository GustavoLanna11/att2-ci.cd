package com.example.disciplinas.repository;

import com.example.disciplinas.model.Disciplina;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class DisciplinaRepository {
    
    private final Map<Long, Disciplina> disciplinas = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1L);
    
    public DisciplinaRepository() {
        // Inicializa com duas disciplinas de exemplo
        inicializarDisciplinas();
    }
    
    private void inicializarDisciplinas() {
        Disciplina disciplina1 = new Disciplina(
            nextId.getAndIncrement(),
            "Desenvolvimento de Aplicações Modernas",
            80,
            "Prof. João Silva",
            "Disciplina focada em desenvolvimento de aplicações usando tecnologias modernas"
        );
        
        Disciplina disciplina2 = new Disciplina(
            nextId.getAndIncrement(),
            "Banco de Dados Avançado",
            60,
            "Prof. Maria Santos",
            "Estudo de técnicas avançadas de modelagem e otimização de bancos de dados"
        );
        
        disciplinas.put(disciplina1.getId(), disciplina1);
        disciplinas.put(disciplina2.getId(), disciplina2);
    }
    
    public List<Disciplina> findAll() {
        return new ArrayList<>(disciplinas.values());
    }
    
    public Optional<Disciplina> findById(Long id) {
        return Optional.ofNullable(disciplinas.get(id));
    }
    
    public Disciplina save(Disciplina disciplina) {
        if (disciplina.getId() == null) {
            disciplina.setId(nextId.getAndIncrement());
        }
        disciplinas.put(disciplina.getId(), disciplina);
        return disciplina;
    }
}

