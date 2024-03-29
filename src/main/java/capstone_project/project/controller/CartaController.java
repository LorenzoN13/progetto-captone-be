package capstone_project.project.controller;

import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.request.CartaRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.service.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carte")
public class CartaController {
    @Autowired
    private CartaService cartaService;

    // Metodo per ottenere tutte le carte paginate
    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllCarte(Pageable pageable) {
        return DefaultResponse.noMessage(cartaService.getAllCarte(pageable), HttpStatus.OK);
    }

    // Metodo per ottenere una carta tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getCartaById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.noMessage(cartaService.getCartaById(id), HttpStatus.OK);
    }

    // Metodo per creare una nuova carta
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createCarta(@RequestBody @Validated CartaRequest cartaRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Creazione della carta e restituzione della risposta HTTP
        return DefaultResponse.noMessage(cartaService.createCarta(cartaRequest), HttpStatus.OK);
    }

    // Metodo per aggiornare una carta esistente
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateCarta(@PathVariable int id, @RequestBody CartaRequest cartaRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Aggiornamento della carta e restituzione della risposta HTTP
        return DefaultResponse.noMessage(cartaService.updateCarta(id, cartaRequest), HttpStatus.OK);
    }

    // Metodo per eliminare una carta esistente
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteCarta(@PathVariable int id) throws NotFoundException {
        // Eliminazione della carta e restituzione della risposta HTTP
        cartaService.deleteCarta(id);
        return DefaultResponse.noObject("La carta con ID " + id + " è stata eliminata", HttpStatus.OK);
    }
}
