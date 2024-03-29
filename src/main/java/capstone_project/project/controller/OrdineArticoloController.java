package capstone_project.project.controller;

import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.request.OrdineArticoloRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.service.OrdineArticoloService;
import capstone_project.project.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordini_articoli")
public class OrdineArticoloController {

    private final OrdineArticoloService ordineArticoloService;

    @Autowired
    public OrdineArticoloController(@Lazy OrdineArticoloService ordineArticoloService) {
        this.ordineArticoloService = ordineArticoloService;
    }

    // Metodo per ottenere tutti gli ordini articoli paginati
    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllOrdineArticoli(Pageable pageable) {
        return DefaultResponse.noMessage(ordineArticoloService.getAllOrdiniArticoli(pageable), HttpStatus.OK);
    }

    // Metodo per ottenere un singolo ordine articolo tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getOrdineArticoloById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.noMessage(ordineArticoloService.getOrdineArticoloById(id), HttpStatus.OK);
    }

    // Metodo per creare un nuovo ordine articolo
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createOrdineArticolo(@RequestBody @Validated OrdineArticoloRequest ordineArticoloRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Creazione dell'ordine articolo e restituzione della risposta HTTP
        return DefaultResponse.noMessage(ordineArticoloService.createOrdineArticolo(ordineArticoloRequest), HttpStatus.OK);
    }

    // Metodo per aggiornare un ordine articolo esistente
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateOrdineArticolo(@PathVariable int id, @RequestBody OrdineArticoloRequest ordineArticoloRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Aggiornamento dell'ordine articolo e restituzione della risposta HTTP
        return DefaultResponse.noMessage(ordineArticoloService.updateOrdineArticolo(id, ordineArticoloRequest), HttpStatus.OK);
    }

    // Metodo per eliminare un ordine articolo esistente
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteOrdineArticolo(@PathVariable int id) throws NotFoundException {
        // Eliminazione dell'ordine articolo e restituzione della risposta HTTP
        ordineArticoloService.deleteOrdineArticolo(id);
        return DefaultResponse.noObject("L'ordine con l'articolo con ID " + id + " è stata eliminata", HttpStatus.OK);
    }
}
