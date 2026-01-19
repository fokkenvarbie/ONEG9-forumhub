package br.com.alura.forumhub.model;

import br.com.alura.forumhub.dto.DadosAtualizacaoTopico;
import br.com.alura.forumhub.dto.DadosCadastroTopico;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private TopicoStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;

    public Topico(DadosCadastroTopico dados, Usuario autor, Curso curso) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.status = TopicoStatus.NAO_RESPONDIDO;
        this.autor = autor;
        this.curso = curso;
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
    }
}