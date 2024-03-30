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

// Annotazione per indicare che questa classe è un servizio Spring
@Service
public class UtenteService {
    // Iniezione della dipendenza del repository UtenteRepository
    @Autowired
    private UtenteRepository utenteRepository;

    // Iniezione della dipendenza dell'encoder per le password
    @Autowired
    private PasswordEncoder encoder;

    // Metodo per trovare un utente tramite il suo username
    public Utente findByUsername(String username) throws NotFoundException {
        Optional<Utente> optionalUser = utenteRepository.findByUsername(username);
        if (optionalUser.isEmpty()) throw new NotFoundException("Utente non trovato");
        return optionalUser.get();
    }

    // Metodo per ottenere tutti gli utenti paginati
    public Page<Utente> getAllUtente(Pageable pageable) {
        return utenteRepository.findAll(pageable);
    }

    // Metodo per ottenere un utente tramite il suo ID
    public Utente getUtenteById(int id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente con id= " + id + " non trovato"));
    }

    // Metodo per salvare un nuovo utente
    public Utente saveUtente(RegisterRequest registerRequest) {
        Utente utente = new Utente();
        utente.setUsername(registerRequest.getUsername());
        utente.setEmail(registerRequest.getEmail());
        utente.setPassword(encoder.encode(registerRequest.getPassword())); // Codifica della password prima di salvarla
        utente.setNome(registerRequest.getNome());
        utente.setCognome(registerRequest.getCognome());
        utente.setRuoli(Set.of(Ruolo.USER)); // Impostazione del ruolo predefinito come USER
        return utenteRepository.save(utente);
    }

    // Metodo per aggiornare le informazioni di un utente
    public Utente updateUtente(int id, UtenteRequest utenteRequest) throws NotFoundException {
        Utente utente = getUtenteById(id);
        utente.setUsername(utenteRequest.getUsername());
        utente.setEmail(utenteRequest.getEmail());
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        return utenteRepository.save(utente);
    }

    // Metodo per eliminare un utente
    public void deleteUtente(int id) throws NotFoundException {
        Utente utente = getUtenteById(id);
        utenteRepository.delete(utente);
    }

    // Metodo per caricare un avatar per un utente
    public Utente uploadAvatar(int id, String url) throws NotFoundException {
        Utente utente = getUtenteById(id);
        utente.setAvatar(url);
        return utenteRepository.save(utente);
    }

    // Metodo per aggiornare il ruolo di un utente ad ADMIN
    public void updateUtenteToAdmin(int id) throws NotFoundException, AlreadyAdminException {
        Utente utente = getUtenteById(id);
        // Verifica se l'utente è già un ADMIN
        if (utente.getRuoli().contains(Ruolo.ADMIN)) throw new AlreadyAdminException("Sei già amministratore");
        utente.addRuolo(Ruolo.ADMIN); // Aggiunta del ruolo ADMIN
        utenteRepository.save(utente);
    }
}
