package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Ordine;
import capstone_project.project.model.OrdineArticolo;
import capstone_project.project.model.Prodotto;
import capstone_project.project.repository.OrdineArticoloRepository;
import capstone_project.project.request.CreaProdottoRequest;
import capstone_project.project.request.OrdineArticoloRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineArticoloService {

    @Autowired
    private OrdineArticoloRepository ordineArticoloRepository;

    @Autowired
    ProdottoService prodottoService;

    @Autowired
    private OrdineService ordineService;

    public Page<OrdineArticolo> getAllOrdiniArticoli (Pageable pageable){
        return ordineArticoloRepository.findAll(pageable);
    }
    public OrdineArticolo getOrdineArticoloById(int id) throws NotFoundException {
        return ordineArticoloRepository.findById(id).orElseThrow(()->new NotFoundException("L'ordine con l'articolo con il numero = " + id + " non è stata trovato"));
    }

    public List<OrdineArticolo> getOrdiniArticoloByOrdineId(int ordineId) {
        return ordineArticoloRepository.findByOrdineId(ordineId);
    }

    public OrdineArticolo createOrdineArticolo(OrdineArticoloRequest oar){
        OrdineArticolo ordineArticolo = new OrdineArticolo();

        ordineArticolo.setQuantita(oar.getQuantita());
        ordineArticolo.setPrezzoScontato(oar.getPrezzoScontato());
        ordineArticolo.setDimensione(oar.getDimensione());

        Ordine ordine = ordineService.getOrdineById(oar.getIdOrdine());
        if (ordine == null) {
            throw new NotFoundException("Ordine non trovato con l'ID specificato.");
        }
        ordineArticolo.setOrdine(ordine);

        Prodotto prodotto = prodottoService.getProdottoById(oar.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }
        ordineArticolo.setProdotto(prodotto);

        return ordineArticoloRepository.save(ordineArticolo);
    }

    public OrdineArticolo updateOrdineArticolo(int id, OrdineArticoloRequest oar){
        OrdineArticolo ordineArticolo = getOrdineArticoloById(id);

        ordineArticolo.setQuantita(oar.getQuantita());
        ordineArticolo.setPrezzoScontato(oar.getPrezzoScontato());
        ordineArticolo.setDimensione(oar.getDimensione());

        Ordine ordine = ordineService.getOrdineById(oar.getIdOrdine());
        if (ordine == null) {
            throw new NotFoundException("Ordine non trovato con l'ID specificato.");
        }
        ordineArticolo.setOrdine(ordine);

        Prodotto prodotto = prodottoService.getProdottoById(oar.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }
        ordineArticolo.setProdotto(prodotto);

        return ordineArticoloRepository.save(ordineArticolo);
    }

    public void deleteOrdineArticolo(int id) throws NotFoundException {
        OrdineArticolo ordineArticolo = getOrdineArticoloById(id);
        ordineArticoloRepository.delete(ordineArticolo);
    }
}
