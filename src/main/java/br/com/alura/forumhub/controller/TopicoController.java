package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.dto.DadosAtualizacaoTopico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import br.com.alura.forumhub.dto.DadosCadastroTopico;
import br.com.alura.forumhub.dto.DadosDetalhamentoTopico;
import br.com.alura.forumhub.model.Topico;
import br.com.alura.forumhub.model.CursoRepositorio;
import br.com.alura.forumhub.model.TopicoRepositorio;
import br.com.alura.forumhub.model.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepositorio repository;

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private CursoRepositorio cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.badRequest().body("Erro: Tópico duplicado.");
        }

        var autor = usuarioRepository.findByNome(dados.nomeAutor());
        var curso = cursoRepository.findByNome(dados.nomeCurso());

        var topico = new Topico(dados, autor, curso);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoTopico>> listar(
            @PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.DESC) Pageable paginacao) {

        var pagina = repository.findAll(paginacao).map(DadosDetalhamentoTopico::new);

        return ResponseEntity.ok(pagina);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = repository.getReferenceById(id);
        topico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}