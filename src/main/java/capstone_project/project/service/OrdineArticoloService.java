package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Ordine;
import capstone_project.project.model.OrdineArticolo;
import capstone_project.project.model.Prodotto;
import capstone_project.project.repository.OrdineArticoloRepository;
import capstone_project.project.request.OrdineArticoloRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

// Annotazione per indicare che questa classe è un servizio Spring
@Service
public class OrdineArticoloService {

    // Iniezione della dipendenza del servizio ProdottoService
    @Autowired
    private ProdottoService prodottoService;

    // Dichiarazione del repository OrdineArticoloRepository e del servizio OrdineService
    private final OrdineArticoloRepository ordineArticoloRepository;
    private final OrdineService ordineService;

    // Costruttore per inizializzare le dipendenze
    @Autowired
    public OrdineArticoloService(OrdineArticoloRepository ordineArticoloRepository, @Lazy OrdineService ordineService) {
        this.ordineArticoloRepository = ordineArticoloRepository;
        this.ordineService = ordineService;
    }

    // Metodo per ottenere tutti gli ordini articoli paginati
    public Page<OrdineArticolo> getAllOrdiniArticoli (Pageable pageable){
        return ordineArticoloRepository.findAll(pageable);
    }

    // Metodo per ottenere un ordine articolo tramite il suo ID
    public OrdineArticolo getOrdineArticoloById(int id) throws NotFoundException {
        return ordineArticoloRepository.findById(id).orElseThrow(()->new NotFoundException("L'ordine con l'articolo con il numero = " + id + " non è stata trovato"));
    }

    // Metodo per ottenere tutti gli ordini articoli associati a un dato ordine
    public List<OrdineArticolo> getOrdiniArticoloByOrdineId(int ordineId) {
        return ordineArticoloRepository.findByOrdineId(ordineId);
    }

    // Metodo per creare un nuovo ordine articolo
    public OrdineArticolo createOrdineArticolo(OrdineArticoloRequest oar){
        OrdineArticolo ordineArticolo = new OrdineArticolo();

        ordineArticolo.setQuantita(oar.getQuantita());
        ordineArticolo.setPrezzoScontato(oar.getPrezzoScontato());
        ordineArticolo.setDimensione(oar.getDimensione());

        // Ottenere l'ordine associato all'ordine articolo
        Ordine ordine = ordineService.getOrdineById(oar.getIdOrdine());
        if (ordine == null) {
            throw new NotFoundException("Ordine non trovato con l'ID specificato.");
        }
        ordineArticolo.setOrdine(ordine);

        // Ottenere il prodotto associato all'ordine articolo
        Prodotto prodotto = prodottoService.getProdottoById(oar.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }
        ordineArticolo.setProdotto(prodotto);

        // Salvare l'ordine articolo nel repository e restituirlo
        return ordineArticoloRepository.save(ordineArticolo);
    }

    // Metodo per aggiornare un ordine articolo esistente
    public OrdineArticolo updateOrdineArticolo(int id, OrdineArticoloRequest oar){
        OrdineArticolo ordineArticolo = getOrdineArticoloById(id);

        ordineArticolo.setQuantita(oar.getQuantita());
        ordineArticolo.setPrezzoScontato(oar.getPrezzoScontato());
        ordineArticolo.setDimensione(oar.getDimensione());

        // Ottenere l'ordine associato all'ordine articolo
        Ordine ordine = ordineService.getOrdineById(oar.getIdOrdine());
        if (ordine == null) {
            throw new NotFoundException("Ordine non trovato con l'ID specificato.");
        }
        ordineArticolo.setOrdine(ordine);

        // Ottenere il prodotto associato all'ordine articolo
        Prodotto prodotto = prodottoService.getProdottoById(oar.getIdProdotto());
        if (prodotto == null) {
            throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
        }
        ordineArticolo.setProdotto(prodotto);

        // Salvare l'ordine articolo aggiornato nel repository e restituirlo
        return ordineArticoloRepository.save(ordineArticolo);
    }

    // Metodo per eliminare un ordine articolo tramite il suo ID
    public void deleteOrdineArticolo(int id) throws NotFoundException {
        // Ottenere l'ordine articolo tramite il suo ID
        OrdineArticolo ordineArticolo = getOrdineArticoloById(id);
        // Eliminare l'ordine articolo dal repository
        ordineArticoloRepository.delete(ordineArticolo);
    }
}
