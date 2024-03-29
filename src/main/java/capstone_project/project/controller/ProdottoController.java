package capstone_project.project.controller;

import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.request.CreaProdottoRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    // Metodo per ottenere tutti i prodotti paginati
    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllProdotti(Pageable pageable) {
        return DefaultResponse.noMessage(prodottoService.getAllProdotti(pageable), HttpStatus.OK);
    }

    // Metodo per ottenere un singolo prodotto tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getProdottoById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.noMessage(prodottoService.getProdottoById(id), HttpStatus.OK);
    }

    // Metodo per creare un nuovo prodotto
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createProdotto(@RequestBody @Validated CreaProdottoRequest creaProdottoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Creazione del prodotto e restituzione della risposta HTTP
        return DefaultResponse.noMessage(prodottoService.createProdotto(creaProdottoRequest), HttpStatus.OK);
    }

    // Metodo per aggiornare un prodotto esistente
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateProdotto(@PathVariable int id, @RequestBody CreaProdottoRequest creaProdottoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Aggiornamento del prodotto e restituzione della risposta HTTP
        return DefaultResponse.noMessage(prodottoService.updateProdotto(id, creaProdottoRequest), HttpStatus.OK);
    }

    // Metodo per eliminare un prodotto esistente
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteProdotto(@PathVariable int id) throws NotFoundException {
        // Eliminazione del prodotto e restituzione della risposta HTTP
        prodottoService.deleteProdotto(id);
        return DefaultResponse.noObject("Il prodotto con ID " + id + " è stato eliminato", HttpStatus.OK);
    }
}
