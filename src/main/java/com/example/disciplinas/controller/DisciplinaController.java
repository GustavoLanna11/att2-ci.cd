package com.example.disciplinas.controller;

import com.example.disciplinas.model.Disciplina;
import com.example.disciplinas.repository.DisciplinaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disciplinas")
@Tag(name = "Disciplinas", description = "API para gerenciamento de disciplinas")
public class DisciplinaController {
    
    private final DisciplinaRepository repository;
    
    public DisciplinaController(DisciplinaRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping
    @Operation(summary = "Listar todas as disciplinas")
    public ResponseEntity<List<Disciplina>> listarTodas() {
        List<Disciplina> disciplinas = repository.findAll();
        return ResponseEntity.ok(disciplinas);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar disciplina por ID")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Long id) {
        Optional<Disciplina> disciplina = repository.findById(id);
        
        if (disciplina.isPresent()) {
            return ResponseEntity.ok(disciplina.get());
        }
        
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    @Operation(summary = "Criar uma nova disciplina")
    public ResponseEntity<Disciplina> criar(@Valid @RequestBody Disciplina disciplina) {
        Disciplina novaDisciplina = repository.save(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDisciplina);
    }
}

