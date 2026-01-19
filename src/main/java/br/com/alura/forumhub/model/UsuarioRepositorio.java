package br.com.alura.forumhub.model;

import br.com.alura.forumhub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
    Usuario findByNome(String nome);
}