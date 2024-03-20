package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Prodotto;
import capstone_project.project.repository.ProdottoRepository;
import capstone_project.project.request.CreaProdottoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private UtenteService utenteService;


    public Page<Prodotto> getAllProdotti(Pageable pageable){
        return prodottoRepository.findAll(pageable);
    }

    public Prodotto getProdottoById(int id) throws NotFoundException {
        return prodottoRepository.findById(id).orElseThrow(()->new NotFoundException("Il prodotto con il numero = " + id + " non è stata trovato"));
    }

    public Prodotto createProdotto(CreaProdottoRequest cpr){
        Prodotto p = new Prodotto();

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

        return prodottoRepository.save(p);

    }

    public Prodotto updateProdotto(int id, CreaProdottoRequest cpr){
        Prodotto p = getProdottoById(id);
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

        return prodottoRepository.save(p);
    }

    public void deleteProdotto(int id) throws NotFoundException {
        Prodotto p = getProdottoById(id);
        prodottoRepository.delete(p);
    }

}
