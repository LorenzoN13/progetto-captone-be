package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.*;
import capstone_project.project.repository.CartaRepository;
import capstone_project.project.request.CartaRequest;
import capstone_project.project.request.DettagliCartaRequest;
import capstone_project.project.request.IndirizzoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

// Annotazione per indicare che questa classe è un servizio Spring
@Service
public class CartaService {

    @Autowired
    CartaRepository cartaRepository;

    @Autowired
    UtenteService utenteService;

    @Autowired
    ProdottoService prodottoService;

    // Metodo per ottenere tutte le carte paginate
    public Page<Carta> getAllCarte(Pageable pageable){
        return cartaRepository.findAll(pageable);
    }

    // Metodo per ottenere una carta tramite il suo ID
    public Carta getCartaById(int id) throws NotFoundException {
        return cartaRepository.findById(id).orElseThrow(() -> new NotFoundException("La carta con ID " + id + " non è stata trovata"));
    }

    // Metodo per creare una nuova carta
    public Carta createCarta(CartaRequest cartaRequest){

        // Ottenere l'utente associato alla carta tramite il suo ID
        Utente utente = utenteService.getUtenteById(cartaRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }

        // Creare una nuova istanza di Carta
        Carta carta = new Carta();

        // Impostare i dettagli della carta
        carta.setUtente(utente);
        carta.setSconto(cartaRequest.getSconto());
        carta.setArticoliTotali(cartaRequest.getArticoliTotali());
        carta.setPrezzoTotale(cartaRequest.getPrezzoTotale());
        carta.setTotalePrezzoScontato(cartaRequest.getTotalePrezzoScontato());

        // Aggiungere i dettagli della carta se presenti
        if (cartaRequest.getDettagliCarta() != null && !cartaRequest.getDettagliCarta().isEmpty()) {
            Set<DettagliCarta> dettagliCartaSet = new HashSet<>();
            for (DettagliCartaRequest dettagliCartaRequest : cartaRequest.getDettagliCarta()) {
                DettagliCarta dettagliCarta = new DettagliCarta();
                dettagliCarta.setCarta(carta);
                dettagliCarta.setDimensione(dettagliCartaRequest.getDimensione());
                dettagliCarta.setPrezzo(dettagliCartaRequest.getPrezzo());
                dettagliCarta.setQuantita(dettagliCartaRequest.getQuantita());
                dettagliCarta.setPrezzoScontato(dettagliCartaRequest.getPrezzoScontato());

                Prodotto prodotto = prodottoService.getProdottoById(dettagliCartaRequest.getIdProdotto());
                if (prodotto == null) {
                    throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
                }

                dettagliCarta.setProdotto(prodotto);

                dettagliCartaSet.add(dettagliCarta);
            }

            carta.setDettaglicarta(dettagliCartaSet);
        }

        // Salvare la carta nel repository e restituirla
        return cartaRepository.save(carta);
    }

    // Metodo per aggiornare una carta esistente
    public Carta updateCarta(int cartaId, CartaRequest cartaRequest) {

        // Ottenere la carta esistente tramite il suo ID
        Carta cartaEsistente = cartaRepository.findById(cartaId).orElseThrow(() -> new NotFoundException("Carta non trovata con ID: " + cartaId));

        // Ottenere l'utente associato alla carta tramite il suo ID
        Utente utente = utenteService.getUtenteById(cartaRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }

        // Impostare i dettagli della carta
        cartaEsistente.setUtente(utente);
        cartaEsistente.setSconto(cartaRequest.getSconto());
        cartaEsistente.setArticoliTotali(cartaRequest.getArticoliTotali());
        cartaEsistente.setPrezzoTotale(cartaRequest.getPrezzoTotale());
        cartaEsistente.setTotalePrezzoScontato(cartaRequest.getTotalePrezzoScontato());

        Set<DettagliCarta> updatedDettagliCartaSet = new HashSet<>();
        // Aggiornare i dettagli della carta se presenti
        if (cartaRequest.getDettagliCarta() != null && !cartaRequest.getDettagliCarta().isEmpty()) {
            for (DettagliCartaRequest dettagliCartaRequest : cartaRequest.getDettagliCarta()) {
                DettagliCarta dettagliCarta = new DettagliCarta();
                dettagliCarta.setCarta(cartaEsistente);
                dettagliCarta.setDimensione(dettagliCartaRequest.getDimensione());
                dettagliCarta.setPrezzo(dettagliCartaRequest.getPrezzo());
                dettagliCarta.setQuantita(dettagliCartaRequest.getQuantita());
                dettagliCarta.setPrezzoScontato(dettagliCartaRequest.getPrezzoScontato());

                Prodotto prodotto = prodottoService.getProdottoById(dettagliCartaRequest.getIdProdotto());
                if (prodotto == null) {
                    throw new NotFoundException("Prodotto non trovato con l'ID specificato.");
                }

                dettagliCarta.setProdotto(prodotto);

                updatedDettagliCartaSet.add(dettagliCarta);
            }
        }
        cartaEsistente.setDettaglicarta(updatedDettagliCartaSet);

        // Salvare la carta aggiornata nel repository e restituirla
        return cartaRepository.save(cartaEsistente);
    }

    // Metodo per eliminare una carta tramite il suo ID
    public void deleteCarta(int id) throws NotFoundException {
        Carta carta = getCartaById(id);
        cartaRepository.delete(carta);
    }
}
