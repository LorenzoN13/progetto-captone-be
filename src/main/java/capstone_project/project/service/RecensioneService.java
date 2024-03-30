package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Prodotto;
import capstone_project.project.model.Recensione;
import capstone_project.project.model.Utente;
import capstone_project.project.repository.RecensioneRepository;
import capstone_project.project.request.CreaProdottoRequest;
import capstone_project.project.request.RecensioneRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Annotazione per indicare che questa classe è un servizio Spring
@Service
public class RecensioneService {

    // Iniezione della dipendenza del repository RecensioneRepository
    @Autowired
    RecensioneRepository recensioneRepository;

    // Iniezione della dipendenza del servizio UtenteService
    @Autowired
    UtenteService utenteService;

    // Iniezione della dipendenza del servizio ProdottoService
    @Autowired
    ProdottoService prodottoService;

    // Metodo per ottenere tutte le recensioni paginate
    public Page<Recensione> getAllRecensioni(Pageable pageable){
        return recensioneRepository.findAll(pageable);
    }

    // Metodo per ottenere una recensione tramite il suo ID
    public Recensione getRecensioneById(int id) throws NotFoundException {
        return recensioneRepository.findById(id).orElseThrow(()->new NotFoundException("La recensione con il numero = " + id + " non è stata trovata"));
    }

    // Metodo per creare una nuova recensione
    public Recensione createRecensione(RecensioneRequest recensioneRequest){
        Recensione recensione = new Recensione();

        // Impostazione del testo della recensione dalla richiesta
        recensione.setRecensione(recensioneRequest.getRecensione());

        // Ottenimento dell'utente tramite l'ID dalla richiesta e verifica dell'esistenza
        Utente utente = utenteService.getUtenteById(recensioneRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }
        recensione.setUtente(utente);

        // Ottenimento del prodotto tramite l'ID dalla richiesta e verifica dell'esistenza
        Prodotto prodotto = prodottoService.getProdottoById(recensioneRequest.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }

        recensione.setProdotto(prodotto);

        // Salvataggio della nuova recensione nel repository e restituzione
        return recensioneRepository.save(recensione);
    }

    // Metodo per aggiornare una recensione esistente
    public Recensione updateRecensione(int id, RecensioneRequest recensioneRequest){
        Recensione recensioneEsistente = getRecensioneById(id);

        // Aggiornamento del testo della recensione dalla richiesta
        recensioneEsistente.setRecensione(recensioneRequest.getRecensione());

        // Ottenimento dell'utente tramite l'ID dalla richiesta e verifica dell'esistenza
        Utente utente = utenteService.getUtenteById(recensioneRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }
        recensioneEsistente.setUtente(utente);

        // Ottenimento del prodotto tramite l'ID dalla richiesta e verifica dell'esistenza
        Prodotto prodotto = prodottoService.getProdottoById(recensioneRequest.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }

        recensioneEsistente.setProdotto(prodotto);

        // Salvataggio della recensione aggiornata nel repository e restituzione
        return recensioneRepository.save(recensioneEsistente);
    }

    // Metodo per eliminare una recensione tramite il suo ID
    public void deleteRecensione(int id) throws NotFoundException {
        Recensione recensioneEsistente = getRecensioneById(id);
        recensioneRepository.delete(recensioneEsistente);
    }
}
