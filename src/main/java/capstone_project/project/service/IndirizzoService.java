package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Indirizzo;
import capstone_project.project.model.Prodotto;
import capstone_project.project.model.Utente;
import capstone_project.project.repository.IndirizzoRepository;
import capstone_project.project.request.CreaProdottoRequest;
import capstone_project.project.request.IndirizzoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    IndirizzoRequest indirizzoRequest;

    @Autowired
    UtenteService utenteService;


    public Page<Indirizzo> getAllIndirizzi(Pageable pageable){
        return indirizzoRepository.findAll(pageable);
    }

    public Indirizzo getIndirizzoById(int id) throws NotFoundException {
        return indirizzoRepository.findById(id).orElseThrow(()->new NotFoundException("L'indirizzo con il numero = " + id + " non è stata trovato"));
    }

    public Indirizzo createIndirizzo(IndirizzoRequest indirizzoRequest){

        Utente utente = utenteService.getUtenteById(indirizzoRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }
        Indirizzo indirizzo = new Indirizzo();

        indirizzo.setVia(indirizzoRequest.getVia());
        indirizzo.setCivico(indirizzoRequest.getCivico());
        indirizzo.setCap(indirizzoRequest.getCap());
        indirizzo.setComune(indirizzoRequest.getComune());
        indirizzo.setUtente(utente);

        return indirizzoRepository.save(indirizzo);
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
