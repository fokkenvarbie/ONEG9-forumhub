package br.com.alura.forumhub.model;

import br.com.alura.forumhub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {
    Curso findByNome(String nome);
}