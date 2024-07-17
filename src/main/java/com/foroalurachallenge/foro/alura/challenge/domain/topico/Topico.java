package com.foroalurachallenge.foro.alura.challenge.domain.topico;

import com.foroalurachallenge.foro.alura.challenge.domain.curso.Curso;
import com.foroalurachallenge.foro.alura.challenge.domain.usuario.Perfil;
import com.foroalurachallenge.foro.alura.challenge.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensaje;
    private LocalDateTime creado;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.NUEVO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;




}
