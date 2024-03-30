package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.*;
import capstone_project.project.repository.OrdineRepository;
import capstone_project.project.request.IndirizzoRequest;
import capstone_project.project.request.OrdineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

// Annotazione per indicare che questa classe è un servizio Spring
@Service
public class OrdineService {

    // Iniezione della dipendenza del servizio UtenteService
    @Autowired
    private UtenteService utenteService;

    // Iniezione della dipendenza del servizio DettagliPagamentoService
    @Autowired
    private DettagliPagamentoService dettagliPagamentoService;

    // Iniezione della dipendenza del servizio IndirizzoService
    @Autowired
    private IndirizzoService indirizzoService;

    // Dichiarazione del repository OrdineRepository e del servizio OrdineArticoloService
    private final OrdineRepository ordineRepository;
    private final OrdineArticoloService ordineArticoloService;

    // Costruttore per inizializzare le dipendenze
    @Autowired
    public OrdineService(OrdineRepository ordineRepository, @Lazy OrdineArticoloService ordineArticoloService) {
        this.ordineRepository = ordineRepository;
        this.ordineArticoloService = ordineArticoloService;
    }

    // Metodo per ottenere tutti gli ordini paginati
    public Page<Ordine> getAllOrdini(Pageable pageable){
        return ordineRepository.findAll(pageable);
    }

    // Metodo per ottenere un ordine tramite il suo ID
    public Ordine getOrdineById(int id) throws NotFoundException {
        return ordineRepository.findById(id).orElseThrow(()->new NotFoundException("L'ordine con il numero = " + id + " non è stata trovato"));
    }

    // Metodo per creare un nuovo ordine
    public Ordine createOrdine(OrdineRequest ordineRequest){

        // Ottenere l'utente associato all'ordine
        Utente utente = utenteService.getUtenteById(ordineRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }

        // Creazione dell'oggetto Ordine
        Ordine ordine = new Ordine();
        ordine.setDataOrdine(ordineRequest.getDataOrdine());
        ordine.setStatoOrdine(ordineRequest.getStatoOrdine());
        ordine.setUtente(utente);
        ordine.setDataConsegna(ordineRequest.getDataConsegna());
        ordine.setArticoloFinale(ordineRequest.getArticoloFinale());

        // Ottenere il dettaglio pagamento associato all'ordine
        DettagliPagamento dettagliPagamento = dettagliPagamentoService.getDettagliPagamentoById(ordineRequest.getIdDettaglioPagamento());
        if (dettagliPagamento == null) {
            throw new NotFoundException("Dettaglio pagamento non trovato con l'ID specificato.");
        }
        ordine.setDettagliPagamento(dettagliPagamento);
        ordine.setSconto(ordineRequest.getSconto());

        // Ottenere l'indirizzo di spedizione associato all'ordine
        Indirizzo indirizzo = indirizzoService.getIndirizzoById(ordineRequest.getIdIndirizzo());
        if (indirizzo == null) {
            throw new NotFoundException("Indirizzo non trovato con l'ID specificato.");
        }
        ordine.setIndirizzoDiSpedizione(indirizzo);
        ordine.setPrezzoScontatoTotale(ordineRequest.getPrezzoScontatoTotale());
        ordine.setPrezzoTotale(ordineRequest.getPrezzoTotale());

        // Ottenere tutti gli ordini articoli associati a questo ordine
        List<OrdineArticolo> ordineArticoli = ordineArticoloService.getOrdiniArticoloByOrdineId(ordineRequest.getIdOrdineArticolo());
        ordine.setOrdineArticoli(ordineArticoli);

        // Salvare l'ordine nel repository e restituirlo
        return ordineRepository.save(ordine);

    }

    // Metodo per aggiornare un ordine esistente
    public Ordine updateOrdine(int id, OrdineRequest ordineRequest){

        // Ottenere l'utente associato all'ordine
        Utente utente = utenteService.getUtenteById(ordineRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }

        // Ottenere l'ordine tramite il suo ID
        Ordine ordine = getOrdineById(id);

        // Aggiornamento dei dati dell'ordine
        ordine.setDataOrdine(ordineRequest.getDataOrdine());
        ordine.setStatoOrdine(ordineRequest.getStatoOrdine());
        ordine.setUtente(utente);
        ordine.setDataConsegna(ordineRequest.getDataConsegna());
        ordine.setArticoloFinale(ordineRequest.getArticoloFinale());

        // Ottenere il dettaglio pagamento associato all'ordine
        DettagliPagamento dettagliPagamento = dettagliPagamentoService.getDettagliPagamentoById(ordineRequest.getIdDettaglioPagamento());
        if (dettagliPagamento == null) {
            throw new NotFoundException("Dettaglio pagamento non trovato con l'ID specificato.");
        }
        ordine.setDettagliPagamento(dettagliPagamento);
        ordine.setSconto(ordineRequest.getSconto());

        // Ottenere l'indirizzo di spedizione associato all'ordine
        Indirizzo indirizzo = indirizzoService.getIndirizzoById(ordineRequest.getIdIndirizzo());
        if (indirizzo == null) {
            throw new NotFoundException("Indirizzo non trovato con l'ID specificato.");
        }
        ordine.setIndirizzoDiSpedizione(indirizzo);
        ordine.setPrezzoScontatoTotale(ordineRequest.getPrezzoScontatoTotale());
        ordine.setPrezzoTotale(ordineRequest.getPrezzoTotale());

        // Ottenere tutti gli ordini articoli associati a questo ordine
        List<OrdineArticolo> ordineArticoli = ordineArticoloService.getOrdiniArticoloByOrdineId(ordineRequest.getIdOrdineArticolo());
        ordine.setOrdineArticoli(ordineArticoli);

        // Salvare l'ordine aggiornato nel repository e restituirlo
        return ordineRepository.save(ordine);
    }

    // Metodo per eliminare un ordine tramite il suo ID
    public void deleteOrdine(int id) throws NotFoundException {
        // Ottenere l'ordine tramite il suo ID
        Ordine ordine = getOrdineById(id);
        // Eliminare l'ordine dal repository
        ordineRepository.delete(ordine);
    }
}
