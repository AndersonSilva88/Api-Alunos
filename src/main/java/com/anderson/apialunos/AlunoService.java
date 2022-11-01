package com.anderson.apialunos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final List<Alunos> alunos;

    public AlunoService() {
        this.alunos = new ArrayList<>();
    }

    public Alunos findById(@PathVariable("id") Integer id) {
        try {
            return this.alunos.stream()
                    .filter(msg -> msg.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Aluno não encontrado");
        }

    }

    public ResponseEntity<Integer> add(@RequestBody Alunos aluno) {
        if (aluno.getId() == null) {
            aluno.setId(alunos.size() + 1);
        }
        alunos.add(aluno);
        return new ResponseEntity<>(aluno.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity update(@RequestBody Alunos aluno) {
        alunos.stream()
                .filter(msg -> msg.getId().equals(aluno.getId()))
                .forEach(msg -> {
                    msg.setNome(aluno.getNome());
                    msg.setIdade(aluno.getIdade());
                });
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity delete(@PathVariable Integer id) {
        alunos.removeIf(msg -> msg.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

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
