package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Prodotto;
import capstone_project.project.repository.ProdottoRepository;
import capstone_project.project.request.CreaProdottoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Annotazione per indicare che questa classe è un servizio Spring
@Service
public class ProdottoService {

    // Iniezione della dipendenza del repository ProdottoRepository
    @Autowired
    private ProdottoRepository prodottoRepository;

    // Iniezione della dipendenza del servizio UtenteService
    @Autowired
    private UtenteService utenteService;

    // Metodo per ottenere tutti i prodotti paginati
    public Page<Prodotto> getAllProdotti(Pageable pageable){
        return prodottoRepository.findAll(pageable);
    }

    // Metodo per ottenere un prodotto tramite il suo ID
    public Prodotto getProdottoById(int id) throws NotFoundException {
        return prodottoRepository.findById(id).orElseThrow(()->new NotFoundException("Il prodotto con il numero = " + id + " non è stata trovato"));
    }

    // Metodo per creare un nuovo prodotto
    public Prodotto createProdotto(CreaProdottoRequest cpr){
        Prodotto p = new Prodotto();

        // Impostazione delle proprietà del prodotto utilizzando i dati dalla richiesta
        p.setTitolo(cpr.getTitolo());
        p.setColore(cpr.getColore());
        p.setDescrizione(cpr.getDescrizione());
        p.setPrezzoScontato(cpr.getPrezzoScontato());
        p.setImmagineUrl(cpr.getImmagineUrl());
        p.setBrand(cpr.getBrand());
        p.setPrezzo(cpr.getPrezzo());
        p.setDimensione(cpr.getDimensione());
        p.setQuantita(cpr.getQuantita());
        p.setCategoria(cpr.getCategoria());

        // Salvataggio del nuovo prodotto nel repository e restituzione
        return prodottoRepository.save(p);
    }

    // Metodo per aggiornare un prodotto esistente
    public Prodotto updateProdotto(int id, CreaProdottoRequest cpr){
        Prodotto p = getProdottoById(id);

        // Aggiornamento delle proprietà del prodotto utilizzando i dati dalla richiesta
        p.setTitolo(cpr.getTitolo());
        p.setColore(cpr.getColore());
        p.setDescrizione(cpr.getDescrizione());
        p.setPrezzoScontato(cpr.getPrezzoScontato());
        p.setImmagineUrl(cpr.getImmagineUrl());
        p.setBrand(cpr.getBrand());
        p.setPrezzo(cpr.getPrezzo());
        p.setDimensione(cpr.getDimensione());
        p.setQuantita(cpr.getQuantita());
        p.setCategoria(cpr.getCategoria());

        // Salvataggio del prodotto aggiornato nel repository e restituzione
        return prodottoRepository.save(p);
    }

    // Metodo per eliminare un prodotto tramite il suo ID
    public void deleteProdotto(int id) throws NotFoundException {
        Prodotto p = getProdottoById(id);
        prodottoRepository.delete(p);
    }

}
