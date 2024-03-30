package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Indirizzo;
import capstone_project.project.model.Prodotto;
import capstone_project.project.model.Utente;
import capstone_project.project.repository.IndirizzoRepository;
import capstone_project.project.request.CreaProdottoRequest;
import capstone_project.project.request.IndirizzoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Annotazione per indicare che questa classe è un servizio Spring
@Service
public class IndirizzoService {

    // Iniezione della dipendenza del repository per gli indirizzi
    @Autowired
    private IndirizzoRepository indirizzoRepository;

    // Iniezione della dipendenza del servizio Utente
    @Autowired
    UtenteService utenteService;

    // Metodo per ottenere tutti gli indirizzi paginati
    public Page<Indirizzo> getAllIndirizzi(Pageable pageable){
        return indirizzoRepository.findAll(pageable);
    }

    // Metodo per ottenere un indirizzo tramite il suo ID
    public Indirizzo getIndirizzoById(int id) throws NotFoundException {
        return indirizzoRepository.findById(id).orElseThrow(()->new NotFoundException("L'indirizzo con ID " + id + " non è stato trovato"));
    }

    // Metodo per creare un nuovo indirizzo
    public Indirizzo createIndirizzo(IndirizzoRequest indirizzoRequest){
        // Ottenere l'utente associato all'indirizzo tramite l'ID fornito nella richiesta
        Utente utente = utenteService.getUtenteById(indirizzoRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }
        // Creare una nuova istanza di Indirizzo
        Indirizzo indirizzo = new Indirizzo();
        // Impostare i dettagli dell'indirizzo
        indirizzo.setVia(indirizzoRequest.getVia());
        indirizzo.setCivico(indirizzoRequest.getCivico());
        indirizzo.setCap(indirizzoRequest.getCap());
        indirizzo.setComune(indirizzoRequest.getComune());
        indirizzo.setUtente(utente);

        // Salvare l'indirizzo nel repository e restituirlo
        return indirizzoRepository.save(indirizzo);
    }

    // Metodo per aggiornare un indirizzo esistente
    public Indirizzo updateIndirizzo(int id, IndirizzoRequest indirizzoRequest){
        // Ottenere l'indirizzo esistente tramite il suo ID
        Indirizzo indirizzo = getIndirizzoById(id);
        // Aggiornare i dettagli dell'indirizzo con quelli forniti nella richiesta
        indirizzo.setVia(indirizzoRequest.getVia());
        indirizzo.setCivico(indirizzoRequest.getCivico());
        indirizzo.setCap(indirizzoRequest.getCap());
        indirizzo.setComune(indirizzoRequest.getComune());

        // Salvare l'indirizzo aggiornato nel repository e restituirlo
        return indirizzoRepository.save(indirizzo);
    }

    // Metodo per eliminare un indirizzo tramite il suo ID
    public void deleteIndirizzo(int id) throws NotFoundException {
        // Ottenere l'indirizzo tramite il suo ID
        Indirizzo indirizzo = getIndirizzoById(id);
        // Eliminare l'indirizzo dal repository
        indirizzoRepository.delete(indirizzo);
    }
}
