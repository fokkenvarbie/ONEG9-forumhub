package br.com.alura.forumhub.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepositorio extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}