package capstone_project.project.security;

import capstone_project.project.exception.UnAuthorizedException;
import capstone_project.project.model.Utente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@PropertySource("application.properties")
public class JwtTools {
    // Variabile per memorizzare la chiave segreta per la generazione e la convalida del token JWT
    @Value("${spring.jwt.secret}")
    private String secret;

    // Variabile per memorizzare la durata di validità del token JWT in millisecondi
    @Value("${spring.jwt.expirationMs}")
    private String expiration;

    // Metodo per creare un token JWT per l'utente specificato
    public String createToken(Utente utente){
        // Costruzione del token JWT con soggetto, data di emissione e data di scadenza
        return "Bearer " + Jwts.builder().subject(utente.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
    }

    // Metodo per convalidare un token JWT
    public void validateToken(String token) throws UnAuthorizedException {
        try {
            // Verifica del token JWT utilizzando la chiave segreta
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception e) {
            // Se il token non è valido, viene lanciata un'eccezione di autorizzazione non autorizzata
            throw new UnAuthorizedException("Il tuo token è scaduto");
        }
    }

    // Metodo per estrarre lo username dall'intestazione del token JWT
    public String extractUsername(String token){
        // Estrarre lo username dal token JWT verificandolo con la chiave segreta
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }
}
