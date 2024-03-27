package capstone_project.project.security;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.exception.UnAuthorizedException;
import capstone_project.project.model.Utente;
import capstone_project.project.service.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter{
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private UtenteService utenteService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = authHeader.substring(7);

        try {
            jwtTools.validateToken(token);
            String email = jwtTools.extractUsername(token);
            Utente utente = utenteService.findByUsername(email);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utente, null, utente.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (UnAuthorizedException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Token non valido\"}");
            return;
        } catch (NotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\": \"Utente non trovato\"}");
            return;
        } catch (Exception e) {
            logger.error("Errore durante l'autenticazione JWT: {}");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        boolean isAuth = new AntPathMatcher().match("/api/auth/**", request.getServletPath());
        boolean isProducts = new AntPathMatcher().match("/api/prodotti/**", request.getServletPath());
        boolean isRecensioni = new AntPathMatcher().match("/api/recensioni/**", request.getServletPath());
        boolean isValutazioni = new AntPathMatcher().match("/api/valutazioni/**", request.getServletPath());
        return isAuth || isProducts || isRecensioni || isValutazioni;
    }
}
