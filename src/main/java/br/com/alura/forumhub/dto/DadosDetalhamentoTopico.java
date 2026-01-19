package br.com.alura.forumhub.dto;

import br.com.alura.forumhub.model.Topico;
import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, String nomeAutor, String nomeCurso) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getAutor().getNome(), topico.getCurso().getNome());
    }
}