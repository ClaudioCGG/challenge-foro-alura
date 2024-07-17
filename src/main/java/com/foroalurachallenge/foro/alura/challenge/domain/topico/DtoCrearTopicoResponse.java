package com.foroalurachallenge.foro.alura.challenge.domain.topico;

import com.foroalurachallenge.foro.alura.challenge.domain.curso.Curso;
import com.foroalurachallenge.foro.alura.challenge.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DtoCrearTopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime creado,
        Estado estado,
        Long autorId,
        Long cursoId
) {
}
