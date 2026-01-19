package br.com.alura.forumhub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "Resposta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private Boolean solucao = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario autor;
}