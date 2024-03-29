package capstone_project.project.controller;

import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.request.OrdineArticoloRequest;
import capstone_project.project.request.OrdineRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordini")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    // Metodo per ottenere tutti gli ordini paginati
    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllOrdini(Pageable pageable) {
        return DefaultResponse.noMessage(ordineService.getAllOrdini(pageable), HttpStatus.OK);
    }

    // Metodo per ottenere un singolo ordine tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getOrdineById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.noMessage(ordineService.getOrdineById(id), HttpStatus.OK);
    }

    // Metodo per creare un nuovo ordine
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createOrdine(@RequestBody @Validated OrdineRequest ordineRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Creazione dell'ordine e restituzione della risposta HTTP
        return DefaultResponse.noMessage(ordineService.createOrdine(ordineRequest), HttpStatus.OK);
    }

    // Metodo per aggiornare un ordine esistente
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateOrdine(@PathVariable int id, @RequestBody OrdineRequest ordineRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Aggiornamento dell'ordine e restituzione della risposta HTTP
        return DefaultResponse.noMessage(ordineService.updateOrdine(id, ordineRequest), HttpStatus.OK);
    }

    // Metodo per eliminare un ordine esistente
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteOrdine(@PathVariable int id) throws NotFoundException {
        // Eliminazione dell'ordine e restituzione della risposta HTTP
        ordineService.deleteOrdine(id);
        return DefaultResponse.noObject("L'ordine con ID " + id + " è stato eliminato", HttpStatus.OK);
    }
}
