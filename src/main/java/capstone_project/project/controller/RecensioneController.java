package capstone_project.project.controller;

import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.request.RecensioneRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.service.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recensioni")
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;

    // Metodo per ottenere tutte le recensioni paginate
    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllRecensioni(Pageable pageable) {
        return DefaultResponse.noMessage(recensioneService.getAllRecensioni(pageable), HttpStatus.OK);
    }

    // Metodo per ottenere una singola recensione tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getRecensioneById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.noMessage(recensioneService.getRecensioneById(id), HttpStatus.OK);
    }

    // Metodo per creare una nuova recensione
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createRecensione(@RequestBody @Validated RecensioneRequest recensioneRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Creazione della recensione e restituzione della risposta HTTP
        return DefaultResponse.noMessage(recensioneService.createRecensione(recensioneRequest), HttpStatus.OK);
    }

    // Metodo per aggiornare una recensione esistente
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateRecensione(@PathVariable int id, @RequestBody RecensioneRequest recensioneRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Aggiornamento della recensione e restituzione della risposta HTTP
        return DefaultResponse.noMessage(recensioneService.updateRecensione(id, recensioneRequest), HttpStatus.OK);
    }

    // Metodo per eliminare una recensione esistente
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteRecensione(@PathVariable int id) throws NotFoundException {
        // Eliminazione della recensione e restituzione della risposta HTTP
        recensioneService.deleteRecensione(id);
        return DefaultResponse.noObject("La recensione con ID " + id + " è stata eliminata", HttpStatus.OK);
    }
}
