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

@Service
public class RecensioneService {

    @Autowired
    RecensioneRepository recensioneRepository;

    @Autowired
    UtenteService utenteService;

    @Autowired
    ProdottoService prodottoService;

    public Page<Recensione> getAllRecensioni(Pageable pageable){
        return recensioneRepository.findAll(pageable);
    }

    public Recensione getRecensioneById(int id) throws NotFoundException {
        return recensioneRepository.findById(id).orElseThrow(()->new NotFoundException("La recensione con il numero = " + id + " non è stata trovata"));
    }

    public Recensione createRecensione(RecensioneRequest recensioneRequest){
        Recensione recensione = new Recensione();

        recensione.setRecensione(recensioneRequest.getRecensione());

        Utente utente = utenteService.getUtenteById(recensioneRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }
        recensione.setUtente(utente);

        Prodotto prodotto = prodottoService.getProdottoById(recensioneRequest.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }

        recensione.setProdotto(prodotto);

        return recensioneRepository.save(recensione);
    }

    public Recensione updateRecensione(int id, RecensioneRequest recensioneRequest){
        Recensione recensioneEsistente = getRecensioneById(id);

        recensioneEsistente.setRecensione(recensioneRequest.getRecensione());

        Utente utente = utenteService.getUtenteById(recensioneRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }
        recensioneEsistente.setUtente(utente);

        Prodotto prodotto = prodottoService.getProdottoById(recensioneRequest.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }

        recensioneEsistente.setProdotto(prodotto);

        return recensioneRepository.save(recensioneEsistente);
    }

    public void deleteRecensione(int id) throws NotFoundException {
        Recensione recensioneEsistente = getRecensioneById(id);
        recensioneRepository.delete(recensioneEsistente);
    }
}
