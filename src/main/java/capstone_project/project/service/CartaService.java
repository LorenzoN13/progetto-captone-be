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

@Service
public class CartaService {

    @Autowired
    CartaRepository cartaRepository;

    @Autowired
    CartaRequest cartaRequest;

    @Autowired
    UtenteService utenteService;

    @Autowired
    ProdottoService prodottoService;


    public Page<Carta> getAllCarte(Pageable pageable){
        return cartaRepository.findAll(pageable);
    }

    public Carta getCartaById(int id) throws NotFoundException {
        return cartaRepository.findById(id).orElseThrow(()->new NotFoundException("La carta con il numero = " + id + " non è stata trovata"));
    }

    public Carta createCarta(CartaRequest cartaRequest){

        Utente utente = utenteService.getUtenteById(cartaRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }

        Carta carta = new Carta();

        carta.setUtente(utente);
        carta.setSconto(cartaRequest.getSconto());
        carta.setArticoliTotali(cartaRequest.getArticoliTotali());
        carta.setPrezzoTotale(cartaRequest.getPrezzoTotale());
        carta.setTotalePrezzoScontato(cartaRequest.getTotalePrezzoScontato());

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

                dettagliCarta.setProdotto(prodotto);
                dettagliCartaSet.add(dettagliCarta);
            }

            carta.setDettaglicarta(dettagliCartaSet);
        }

        return cartaRepository.save(carta);
    }

    public Carta updateCarta(int cartaId, CartaRequest cartaRequest) {

        Carta cartaEsistente = cartaRepository.findById(cartaId).orElseThrow(() -> new NotFoundException("Carta non trovata con ID: " + cartaId));


        Utente utente = utenteService.getUtenteById(cartaRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }


        cartaEsistente.setUtente(utente);
        cartaEsistente.setSconto(cartaRequest.getSconto());
        cartaEsistente.setArticoliTotali(cartaRequest.getArticoliTotali());
        cartaEsistente.setPrezzoTotale(cartaRequest.getPrezzoTotale());
        cartaEsistente.setTotalePrezzoScontato(cartaRequest.getTotalePrezzoScontato());

        Set<DettagliCarta> updatedDettagliCartaSet = new HashSet<>();
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


        return cartaRepository.save(cartaEsistente);
    }

    public void deleteCarta(int id) throws NotFoundException {
        Carta carta = getCartaById(id);
        cartaRepository.delete(carta);
    }

}
