package com.foroalurachallenge.foro.alura.challenge.domain.topico;

import com.foroalurachallenge.foro.alura.challenge.domain.curso.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCrearTopicoRequest(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long cursoId
) {
}
