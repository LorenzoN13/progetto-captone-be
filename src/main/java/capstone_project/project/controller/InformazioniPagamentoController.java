package capstone_project.project.controller;

import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.request.DettagliPagamentoRequest;
import capstone_project.project.request.InformazioniPagamentoRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.service.InformazioniPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/informazioni_pagamenti")
public class InformazioniPagamentoController {

    @Autowired
    private InformazioniPagamentoService informazioniPagamentoService;

    // Metodo per ottenere tutte le informazioni di pagamento paginate
    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllInformazioniPagamenti(Pageable pageable) {
        return DefaultResponse.noMessage(informazioniPagamentoService.getAllInformazioniPagamenti(pageable), HttpStatus.OK);
    }

    // Metodo per ottenere una singola informazione di pagamento tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getInformazioniPagamentoById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.noMessage(informazioniPagamentoService.getInformazioniPagamentoById(id), HttpStatus.OK);
    }

    // Metodo per creare nuove informazioni di pagamento
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createInformazioniPagamento(@RequestBody @Validated InformazioniPagamentoRequest informazioniPagamentoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Creazione delle informazioni di pagamento e restituzione della risposta HTTP
        return DefaultResponse.noMessage(informazioniPagamentoService.createInformazioniPagamento(informazioniPagamentoRequest), HttpStatus.OK);
    }

    // Metodo per aggiornare le informazioni di pagamento esistenti
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateInformazioniPagamento(@PathVariable int id, @RequestBody InformazioniPagamentoRequest informazioniPagamentoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Aggiornamento delle informazioni di pagamento e restituzione della risposta HTTP
        return DefaultResponse.noMessage(informazioniPagamentoService.updateInformazioniPagamento(id, informazioniPagamentoRequest), HttpStatus.OK);
    }

    // Metodo per eliminare le informazioni di pagamento esistenti
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteInformazioniPagamento(@PathVariable int id) throws NotFoundException {
        // Eliminazione delle informazioni di pagamento e restituzione della risposta HTTP
        informazioniPagamentoService.deleteInformazioniPagamento(id);
        return DefaultResponse.noObject("L'informazione con il pagamento con ID " + id + " è stata eliminata", HttpStatus.OK);
    }

}
