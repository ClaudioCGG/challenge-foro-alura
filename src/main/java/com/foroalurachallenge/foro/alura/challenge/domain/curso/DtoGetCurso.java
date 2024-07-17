package com.foroalurachallenge.foro.alura.challenge.domain.curso;

public record DtoGetCurso(Long id, String nombre, String categoria) {
    public DtoGetCurso(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria().toString());
    }

}
