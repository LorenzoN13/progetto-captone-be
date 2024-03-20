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

@Service
public class ValutazioneService {

    @Autowired
    private ValutazioneRepository valutazioneRepository;

    @Autowired
    UtenteService utenteService;

    @Autowired
    ProdottoService prodottoService;

    public Page<Valutazione> getAllValutazioni(Pageable pageable){
        return valutazioneRepository.findAll(pageable);
    }

    public Valutazione getValutazioneById(int id) throws NotFoundException {
        return valutazioneRepository.findById(id).orElseThrow(()->new NotFoundException("La valutazione con il numero = " + id + " non è stata trovata"));
    }

    public Valutazione createValutazione(ValutazioneRequest valutazioneRequest) {

        Utente utente = utenteService.getUtenteById(valutazioneRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }
        Valutazione valutazione = new Valutazione();

        valutazione.setValutazione(valutazioneRequest.getValutazione());
        valutazione.setUtente(utente);


        Prodotto prodotto = prodottoService.getProdottoById(valutazioneRequest.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }
        valutazione.setProdotto(prodotto);

        return valutazioneRepository.save(valutazione);
    }


    public Valutazione updateValutazione(int id, ValutazioneRequest valutazioneRequest){
        Valutazione valutazioneEsistente  = getValutazioneById(id);

        Utente utente = utenteService.getUtenteById(valutazioneRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }

        valutazioneEsistente.setValutazione(valutazioneRequest.getValutazione());
        valutazioneEsistente.setUtente(utente);

        Prodotto prodotto = prodottoService.getProdottoById(valutazioneRequest.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }
        valutazioneEsistente.setProdotto(prodotto);


        return valutazioneRepository.save(valutazioneEsistente);
    }

    public void deleteValutazione(int id) throws NotFoundException {
        Valutazione valutazione = getValutazioneById(id);
        valutazioneRepository.delete(valutazione);
    }
}
