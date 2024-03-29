package capstone_project.project.controller;

import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.request.ValutazioneRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.service.ValutazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/valutazione")
public class ValutazioneController {

    @Autowired
    private ValutazioneService valutazioneService;

    // Metodo per ottenere tutte le valutazioni paginate
    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllValutazioni(Pageable pageable) {
        return DefaultResponse.noMessage(valutazioneService.getAllValutazioni(pageable), HttpStatus.OK);
    }

    // Metodo per ottenere una singola valutazione tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getValutazioneById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.noMessage(valutazioneService.getValutazioneById(id), HttpStatus.OK);
    }

    // Metodo per creare una nuova valutazione
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createValutazione(@RequestBody @Validated ValutazioneRequest valutazioneRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Creazione della valutazione e restituzione della risposta HTTP
        return DefaultResponse.noMessage(valutazioneService.createValutazione(valutazioneRequest), HttpStatus.OK);
    }

    // Metodo per aggiornare una valutazione esistente
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateValutazione(@PathVariable int id, @RequestBody ValutazioneRequest valutazioneRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Aggiornamento della valutazione e restituzione della risposta HTTP
        return DefaultResponse.noMessage(valutazioneService.updateValutazione(id, valutazioneRequest), HttpStatus.OK);
    }

    // Metodo per eliminare una valutazione esistente
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteValutazione(@PathVariable int id) throws NotFoundException {
        // Eliminazione della valutazione e restituzione della risposta HTTP
        valutazioneService.deleteValutazione(id);
        return DefaultResponse.noObject("La valutazione con ID " + id + " è stata eliminata", HttpStatus.OK);
    }
}
