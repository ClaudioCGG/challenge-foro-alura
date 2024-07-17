package com.foroalurachallenge.foro.alura.challenge.controller;

import com.foroalurachallenge.foro.alura.challenge.domain.curso.Curso;
import com.foroalurachallenge.foro.alura.challenge.domain.curso.CursoRepository;
import com.foroalurachallenge.foro.alura.challenge.domain.topico.*;
import com.foroalurachallenge.foro.alura.challenge.domain.usuario.Usuario;
import com.foroalurachallenge.foro.alura.challenge.domain.usuario.UsuarioRepository;
import com.foroalurachallenge.foro.alura.challenge.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

//    @Autowired
//    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<DtoCrearTopicoResponse> registrarTopico(@RequestBody @Valid DtoCrearTopicoRequest dtoCrearTopicoRequest, UriComponentsBuilder uriComponentsBuilder) {
        // Obtener el usuario actualmente autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = (Usuario) usuarioRepository.findByMail(username);

        // Obtener el curso correspondiente
        Curso curso = cursoRepository.findById(dtoCrearTopicoRequest.cursoId()).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Crear el Topico
        Topico topico = new Topico(null, dtoCrearTopicoRequest.titulo(), dtoCrearTopicoRequest.mensaje(), LocalDateTime.now(), Estado.NUEVO, usuario, curso);
        topico = topicoRepository.save(topico);

        // Crear el URI de la respuesta
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        // Crear el DTO de respuesta
        DtoCrearTopicoResponse response = new DtoCrearTopicoResponse(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCreado(), topico.getEstado(), topico.getAutor().getId(), topico.getCurso().getId());

        return ResponseEntity.created(uri).body(response);
    }

}
