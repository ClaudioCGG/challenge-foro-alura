package com.foroalurachallenge.foro.alura.challenge.domain.respuesta;

import com.foroalurachallenge.foro.alura.challenge.domain.curso.Curso;
import com.foroalurachallenge.foro.alura.challenge.domain.topico.Estado;
import com.foroalurachallenge.foro.alura.challenge.domain.topico.Topico;
import com.foroalurachallenge.foro.alura.challenge.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @NotNull
    @Future
    private LocalDateTime creado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_apodo")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;


}
