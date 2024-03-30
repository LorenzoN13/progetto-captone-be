package capstone_project.project.security;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.exception.UnAuthorizedException;
import capstone_project.project.model.Utente;
import capstone_project.project.service.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter{
   // Iniezione delle dipendenze
    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private UtenteService utenteService;

    // Logger per la registrazione degli eventi
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    // Metodo che viene eseguito per ogni richiesta HTTP
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        // Se l'header di autorizzazione non è presente o non inizia con "Bearer ", restituisci un errore di autorizzazione.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Estrai il token JWT dall'header.
        String token = authHeader.substring(7);

        try {
            // Valida il token JWT.
            jwtTools.validateToken(token);

            // Estrai l'email dall'intestazione JWT.
            String email = jwtTools.extractUsername(token);

            // Cerca l'utente nel database utilizzando l'email.
            Utente utente = utenteService.findByUsername(email);

            // Crea un oggetto di autenticazione basato sull'utente trovato.
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utente, null, utente.getAuthorities());

            // Imposta l'oggetto di autenticazione nel contesto di sicurezza di Spring.
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Passa la richiesta e la risposta alla catena di filtri successiva.
            filterChain.doFilter(request, response);
        } catch (UnAuthorizedException e) {
            // Se il token JWT non è valido, restituisci un errore di autorizzazione.
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Token non valido\"}");
            return;
        } catch (NotFoundException e) {
            // Se l'utente non è trovato nel database, restituisci un errore di non trovato.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\": \"Utente non trovato\"}");
            return;
        } catch (Exception e) {
            // Se si verifica un'eccezione durante l'autenticazione JWT, registra l'errore e restituisci un errore interno del server.
            logger.error("Errore durante l'autenticazione JWT: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
    }

    // Metodo per determinare se il filtro deve essere eseguito per una determinata richiesta.
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        // Verifica se la richiesta corrente corrisponde ai percorsi che non richiedono autenticazione JWT.
        boolean isAuth = new AntPathMatcher().match("/api/auth/**", request.getServletPath());
        boolean isProducts = new AntPathMatcher().match("/api/prodotti/**", request.getServletPath());
        boolean isRecensioni = new AntPathMatcher().match("/api/recensioni/**", request.getServletPath());
        boolean isValutazioni = new AntPathMatcher().match("/api/valutazioni/**", request.getServletPath());
        return isAuth || isProducts || isRecensioni || isValutazioni;
    }
}
