package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Indirizzo;
import capstone_project.project.model.Ordine;
import capstone_project.project.model.OrdineArticolo;
import capstone_project.project.model.Utente;
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
        ordine.setDettagliPagamento();
        ordine.setSconto(ordineRequest.getSconto());
        ordine.setIndirizzoDiSpedizione();
        ordine.setPrezzoScontatoTotale(ordineRequest.getPrezzoScontatoTotale());
        ordine.setPrezzoTotale(ordineRequest.getPrezzoTotale());

        List<OrdineArticolo> ordineArticoli = ordineArticoloService.getOrdiniArticoloByOrdineId(ordineRequest.getIdOrdineArticolo());
        if (ordineArticoli.isEmpty()) {
            throw new NotFoundException("Nessun ordine articolo trovato con l'ID specificato.");
        }
        ordine.setOrdineArticoli(ordineArticoli);

    }

    public Indirizzo updateIndirizzo(int id, IndirizzoRequest indirizzoRequest){
        Indirizzo indirizzo = getIndirizzoById(id);
        indirizzo.setVia(indirizzoRequest.getVia());
        indirizzo.setCivico(indirizzoRequest.getCivico());
        indirizzo.setCap(indirizzoRequest.getCap());
        indirizzo.setComune(indirizzoRequest.getComune());

        return indirizzoRepository.save(indirizzo);
    }

    public void deleteIndirizzo(int id) throws NotFoundException {
        Indirizzo indirizzo = getIndirizzoById(id);
        indirizzoRepository.delete(indirizzo);
    }
}
