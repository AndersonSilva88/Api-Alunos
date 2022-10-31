package com.anderson.apialunos;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunosController {

    private final List<Alunos> alunos;

    public AlunosController() {
        this.alunos = new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Alunos findById(@PathVariable("id") Integer id) {
        return this.alunos.stream()
                .filter(msg -> msg.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Alunos aluno) {
        if (aluno.getId() == null) {
            aluno.setId(alunos.size() + 1);
        }

        alunos.add(aluno);
        return new ResponseEntity<>(aluno.getId(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Alunos aluno) {
        alunos.stream()
                .filter(msg -> msg.getId().equals(aluno.getId()))
                .forEach(msg -> {
                    msg.setNome(aluno.getNome());
                    msg.setIdade(aluno.getIdade());
                });
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        alunos.removeIf(msg -> msg.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<Alunos> findAll(@RequestParam(required = false) String nome, Integer idade) {
        if (nome != null) {
            return alunos.stream()
                    .filter(msg -> msg.getNome().contains(nome))
                    .filter(msg -> msg.getIdade().equals(idade))
                    .collect(Collectors.toList());
        }
        return alunos;
    }


}
