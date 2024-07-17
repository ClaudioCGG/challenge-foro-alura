package com.foroalurachallenge.foro.alura.challenge.controller;

import com.foroalurachallenge.foro.alura.challenge.domain.curso.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DtoPostResponseCurso> registrarCurso(@RequestBody @Valid
                                                                   DtoPostRequestCurso dtoPostRequestCurso,
                                                               UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoRepository.save(new Curso(dtoPostRequestCurso));
        DtoPostResponseCurso dtoPostResponseCurso = new DtoPostResponseCurso(
                curso.getNombre(),
                curso.getCategoria().toString());
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(dtoPostResponseCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DtoGetCurso>> listadoCurso(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(DtoGetCurso::new));
    }


}



