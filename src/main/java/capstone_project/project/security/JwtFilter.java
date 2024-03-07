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
        String auth= request.getHeader("Authorization");

        if(auth==null|| !auth.startsWith("Bearer "))
            try {throw new UnAuthorizedException("Token mancante!");}
            catch (UnAuthorizedException e) {throw new RuntimeException(e);}

        String token=auth.substring(7);
        try {jwtTools.validateToken(token);}
        catch (UnAuthorizedException e) {throw new RuntimeException(e);}

        String email= jwtTools.extractUsername(token);
        try {
            Utente utente = utenteService.findByUsername(email);
            System.out.println(utente.getAuthorities());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utente, null,utente.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
        }
        catch (NotFoundException e) {throw new RuntimeException(e);}
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return new AntPathMatcher().match("/api/auth/**", request.getServletPath());
    }
}
