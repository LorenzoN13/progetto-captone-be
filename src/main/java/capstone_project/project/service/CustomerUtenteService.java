package capstone_project.project.service;

import capstone_project.project.model.Utente;
import capstone_project.project.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomerUtenteService implements UserDetailsService {
    @Autowired
    private UtenteRepository utenteRepository;

    public CustomerUtenteService(UtenteRepository utenteRepository){
        this.utenteRepository = utenteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByEmail(username);
        if (utente == null){
            throw new UsernameNotFoundException("utente non trovato con questa l'e-mail -" + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        return new User(utente.getEmail(), utente.getPassword(), authorities);
    }
}
