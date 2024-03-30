package capstone_project.project.security;

import capstone_project.project.Enum.Ruolo;
import com.cloudinary.provisioning.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityChain {
    // Iniezione delle dipendenze
    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private JwtFilter jwtFilter;

    // Definisce una catena di filtri di sicurezza per le richieste HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Disabilita la protezione CSRF
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // Configura le politiche CORS
        httpSecurity.cors(Customizer.withDefaults());

        // Aggiunge il filtro JWT prima del filtro di autenticazione UsernamePasswordAuthenticationFilter
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // Configura le autorizzazioni per gli endpoint, permettendo l'accesso a tutti gli endpoint che iniziano con "/api/"
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/**").permitAll());

        // Restituisce la catena di filtri di sicurezza configurata
        return httpSecurity.build();
    }

    // Configura le politiche CORS per consentire le richieste provenienti da localhost sulla porta 4200
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Crea una nuova configurazione CORS
        CorsConfiguration config = new CorsConfiguration();

        // Imposta gli origini consentiti, i metodi consentiti e le intestazioni consentite
        config.setAllowedOrigins(List.of("http://localhost:4200/"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));

        // Crea una fonte di configurazione CORS basata sugli URL
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Registra la configurazione CORS per tutti gli endpoint del server ("/")
        source.registerCorsConfiguration("/**", config);

        // Restituisce la fonte di configurazione CORS configurata
        return source;
    }
}
