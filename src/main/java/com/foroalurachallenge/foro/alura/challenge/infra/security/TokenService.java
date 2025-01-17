package com.foroalurachallenge.foro.alura.challenge.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.foroalurachallenge.foro.alura.challenge.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("apiSecret");
            return JWT.create()
                    .withIssuer("Foro Alura Challenge")
                    .withSubject(usuario.getMail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject (String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("apiSecret");

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Foro Alura Challenge")
                    .build();

            return verifier.verify(token).getSubject();
        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
            throw new RuntimeException("Verifier invalido");
        }

    }

    public Long getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("apiSecret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Foro Alura Challenge")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("id").asLong();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Verifier inválido");
        }
    }

}

