package com.anderson.apialunos;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunosController {

    AlunoService alunoService = new AlunoService();

    private final List<Alunos> alunos;

    public AlunosController() {
        this.alunos = new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Alunos findById(@PathVariable("id") Integer id) {
      try {
          return alunoService.findById(id);
      } catch (Exception e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Alunos aluno) {
      try {
          return alunoService.add(aluno);
      } catch (Exception e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Alunos aluno) {
      try {
          return alunoService.update(aluno);
      } catch (Exception e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            return alunoService.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Alunos> findAll(@RequestParam(required = false) String nome, Integer idade) {
       try {
           return alunoService.findAll(nome,idade);
       } catch (Exception e) {
           throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
    }


}
