package br.com.alura.forumhub.dto;

import br.com.alura.forumhub.model.TopicoStatus;
import br.com.alura.forumhub.model.Topico;
import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        TopicoStatus status,
        String autor,
        String curso
) {
    public DadosListagemTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );
    }
}