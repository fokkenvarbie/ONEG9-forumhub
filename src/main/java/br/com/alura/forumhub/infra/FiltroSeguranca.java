package br.com.alura.forumhub.infra;

import br.com.alura.forumhub.model.UsuarioRepositorio; // Verifique se o nome é esse
import br.com.alura.forumhub.service.TokenServico;     // Verifique se termina com 'o'
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class FiltroSeguranca extends OncePerRequestFilter {

    @Autowired
    private TokenServico tokenServico;

    @Autowired
    private UsuarioRepositorio repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var tokenJWT = recuperarToken(request);
            if (tokenJWT != null) {
                var subject = tokenServico.getSubject(tokenJWT);
                var usuario = repository.findByEmail(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}