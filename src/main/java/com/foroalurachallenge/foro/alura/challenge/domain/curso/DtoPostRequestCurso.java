package com.foroalurachallenge.foro.alura.challenge.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DtoPostRequestCurso(
        String nombre,
        Categoria categoria) {
}
