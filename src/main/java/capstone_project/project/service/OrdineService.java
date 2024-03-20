package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.*;
import capstone_project.project.repository.OrdineRepository;
import capstone_project.project.request.IndirizzoRequest;
import capstone_project.project.request.OrdineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private OrdineArticoloService ordineArticoloService;

    @Autowired
    private DettagliPagamentoService dettagliPagamentoService;

    @Autowired
    private IndirizzoService indirizzoService;


    public Page<Ordine> getAllOrdini(Pageable pageable){
        return ordineRepository.findAll(pageable);
    }

    public Ordine getOridneById(int id) throws NotFoundException {
        return ordineRepository.findById(id).orElseThrow(()->new NotFoundException("L'ordine con il numero = " + id + " non è stata trovato"));
    }

    public Ordine createOrdine(OrdineRequest ordineRequest){

        Utente utente = utenteService.getUtenteById(ordineRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }
        Ordine ordine = new Ordine();
        ordine.setDataOrdine(ordineRequest.getDataOrdine());
        ordine.setStatoOrdine(ordineRequest.getStatoOrdine());
        ordine.setUtente(utente);
        ordine.setDataConsegna(ordineRequest.getDataConsegna());
        ordine.setArticoloFinale(ordineRequest.getArticoloFinale());

        DettagliPagamento dettagliPagamento = dettagliPagamentoService.getDettagliPagamentoById(ordineRequest.getIdDettaglioPagamento());
        if (dettagliPagamento == null) {
            throw new NotFoundException("Dettaglio pagameto non trovato con l'ID specificato.");
        }
        ordine.setDettagliPagamento(dettagliPagamento);
        ordine.setSconto(ordineRequest.getSconto());

        Indirizzo indirizzo = indirizzoService.getIndirizzoById(ordineRequest.getIdIndirizzo());
        if (indirizzo == null) {
            throw new NotFoundException("Indirizzo non trovato con l'ID specificato.");
        }

        ordine.setIndirizzoDiSpedizione(indirizzo);
        ordine.setPrezzoScontatoTotale(ordineRequest.getPrezzoScontatoTotale());
        ordine.setPrezzoTotale(ordineRequest.getPrezzoTotale());

        List<OrdineArticolo> ordineArticoli = ordineArticoloService.getOrdiniArticoloByOrdineId(ordineRequest.getIdOrdineArticolo());
        ordine.setOrdineArticoli(ordineArticoli);

        return ordineRepository.save(ordine);

    }

    public Ordine updateOrdine(int id, OrdineRequest ordineRequest){

        Utente utente = utenteService.getUtenteById(ordineRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }
        Ordine ordine = getOridneById(id);

        ordine.setDataOrdine(ordineRequest.getDataOrdine());
        ordine.setStatoOrdine(ordineRequest.getStatoOrdine());
        ordine.setUtente(utente);
        ordine.setDataConsegna(ordineRequest.getDataConsegna());
        ordine.setArticoloFinale(ordineRequest.getArticoloFinale());

        DettagliPagamento dettagliPagamento = dettagliPagamentoService.getDettagliPagamentoById(ordineRequest.getIdDettaglioPagamento());
        if (dettagliPagamento == null) {
            throw new NotFoundException("Dettaglio pagameto non trovato con l'ID specificato.");
        }
        ordine.setDettagliPagamento(dettagliPagamento);
        ordine.setSconto(ordineRequest.getSconto());

        Indirizzo indirizzo = indirizzoService.getIndirizzoById(ordineRequest.getIdIndirizzo());
        if (indirizzo == null) {
            throw new NotFoundException("Indirizzo non trovato con l'ID specificato.");
        }

        ordine.setIndirizzoDiSpedizione(indirizzo);
        ordine.setPrezzoScontatoTotale(ordineRequest.getPrezzoScontatoTotale());
        ordine.setPrezzoTotale(ordineRequest.getPrezzoTotale());

        List<OrdineArticolo> ordineArticoli = ordineArticoloService.getOrdiniArticoloByOrdineId(ordineRequest.getIdOrdineArticolo());
        ordine.setOrdineArticoli(ordineArticoli);

       return ordineRepository.save(ordine);
    }

    public void deleteOrdine(int id) throws NotFoundException {
        Ordine ordine = getOridneById(id);
        ordineRepository.delete(ordine);
    }
}
