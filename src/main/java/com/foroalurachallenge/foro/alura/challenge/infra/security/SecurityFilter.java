package com.foroalurachallenge.foro.alura.challenge.infra.security;

import com.foroalurachallenge.foro.alura.challenge.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Obtener el token del header
        var authHeader = request.getHeader("Authorization");

        // muevo el replace del bearer
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var mailUsuario = tokenService.getSubject(token);// Extrae el username
            System.out.println(mailUsuario);
            if (mailUsuario != null) {
                //Token valido
                var usuario = usuarioRepository.findByMail(mailUsuario);
                var autentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities()); /// Forzamos inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(autentication);
            }
        }

        filterChain.doFilter(request,response);
        //else {throw new RuntimeException("El token enviado es inválido..");}
    }

}
