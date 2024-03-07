package capstone_project.project.service;

import capstone_project.project.Enum.Ruolo;
import capstone_project.project.exception.AlreadyAdminException;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Utente;
import capstone_project.project.repository.UtenteRepository;
import capstone_project.project.request.RegisterRequest;
import capstone_project.project.request.UtenteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder encoder;

    public Utente findByUsername(String username) throws NotFoundException {
        Optional<Utente> optionalUser = utenteRepository.findByUsername(username);
        if(optionalUser.isEmpty())throw new NotFoundException("Utente non trovato");
        return optionalUser.get();
    }

    public Page<Utente> getAllUsers(Pageable pageable) {
        return utenteRepository.findAll(pageable);
    }

    public Utente getUserById(int id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(()-> new NotFoundException("Utente con id= " + id + " non trovato"));
    }
    public Utente saveUtente(RegisterRequest registerRequest) {
        Utente utente = new Utente();
        utente.setUsername(registerRequest.getUsername());
        utente.setEmail(registerRequest.getEmail());
        utente.setPassword(encoder.encode(registerRequest.getPassword()));
        utente.setNome(registerRequest.getNome());
        utente.setCognome(registerRequest.getCognome());
        utente.setRuoli(Set.of(Ruolo.USER));
        return utenteRepository.save(utente);
    }

    public Utente updateUtente(int id, UtenteRequest utenteRequest) throws NotFoundException {
        Utente utente = getUserById(id);
        utente.setUsername(utenteRequest.getUsername());
        utente.setEmail(utenteRequest.getEmail());
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());

        return utenteRepository.save(utente);
    }

    public void deleteUtente(int id) throws NotFoundException {
        Utente utente = getUserById(id);
        utenteRepository.delete(utente);
    }

    public Utente uploadAvatar(int id, String url) throws NotFoundException{
        Utente utente = getUserById(id);
        utente.setAvatar(url);
        return utenteRepository.save(utente);
    }

    public void updateUtenteToAdmin(int id) throws NotFoundException, AlreadyAdminException {
        Utente utente = getUserById(id);
        if(utente.getRuoli().contains(Ruolo.ADMIN)) throw new AlreadyAdminException("Sei già amministratore");
        utente.addRuolo(Ruolo.ADMIN);
        utenteRepository.save(utente);
    }
}
