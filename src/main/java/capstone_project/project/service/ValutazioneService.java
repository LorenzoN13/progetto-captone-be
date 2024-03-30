package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.*;
import capstone_project.project.repository.ValutazioneRepository;
import capstone_project.project.request.IndirizzoRequest;
import capstone_project.project.request.ValutazioneRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Annotazione per indicare che questa classe è un servizio Spring
@Service
public class ValutazioneService {

    // Iniezione della dipendenza del repository ValutazioneRepository
    @Autowired
    private ValutazioneRepository valutazioneRepository;

    // Iniezione della dipendenza del servizio UtenteService
    @Autowired
    UtenteService utenteService;

    // Iniezione della dipendenza del servizio ProdottoService
    @Autowired
    ProdottoService prodottoService;

    // Metodo per ottenere tutte le valutazioni paginate
    public Page<Valutazione> getAllValutazioni(Pageable pageable){
        return valutazioneRepository.findAll(pageable);
    }

    // Metodo per ottenere una valutazione tramite il suo ID
    public Valutazione getValutazioneById(int id) throws NotFoundException {
        return valutazioneRepository.findById(id).orElseThrow(() -> new NotFoundException("La valutazione con il numero = " + id + " non è stata trovata"));
    }

    // Metodo per creare una nuova valutazione
    public Valutazione createValutazione(ValutazioneRequest valutazioneRequest) {

        // Ottieni l'utente associato alla valutazione
        Utente utente = utenteService.getUtenteById(valutazioneRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }

        // Crea una nuova istanza di Valutazione
        Valutazione valutazione = new Valutazione();

        // Imposta i valori della valutazione
        valutazione.setValutazione(valutazioneRequest.getValutazione());
        valutazione.setUtente(utente);

        // Ottieni il prodotto associato alla valutazione
        Prodotto prodotto = prodottoService.getProdottoById(valutazioneRequest.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }
        valutazione.setProdotto(prodotto);

        // Salva la valutazione nel repository e restituisci l'oggetto salvato
        return valutazioneRepository.save(valutazione);
    }

    // Metodo per aggiornare una valutazione esistente
    public Valutazione updateValutazione(int id, ValutazioneRequest valutazioneRequest){
        // Ottieni la valutazione esistente tramite l'ID
        Valutazione valutazioneEsistente  = getValutazioneById(id);

        // Ottieni l'utente associato alla valutazione
        Utente utente = utenteService.getUtenteById(valutazioneRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }

        // Imposta i nuovi valori della valutazione
        valutazioneEsistente.setValutazione(valutazioneRequest.getValutazione());
        valutazioneEsistente.setUtente(utente);

        // Ottieni il prodotto associato alla valutazione
        Prodotto prodotto = prodottoService.getProdottoById(valutazioneRequest.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }
        valutazioneEsistente.setProdotto(prodotto);

        // Salva la valutazione aggiornata nel repository e restituisci l'oggetto salvato
        return valutazioneRepository.save(valutazioneEsistente);
    }

    // Metodo per eliminare una valutazione
    public void deleteValutazione(int id) throws NotFoundException {
        // Ottieni la valutazione tramite l'ID e poi eliminale
        Valutazione valutazione = getValutazioneById(id);
        valutazioneRepository.delete(valutazione);
    }
}
