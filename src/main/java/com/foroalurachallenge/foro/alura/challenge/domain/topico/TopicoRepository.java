package com.foroalurachallenge.foro.alura.challenge.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository  extends JpaRepository<Topico, Long> {
    Topico save(Topico topico);
}
