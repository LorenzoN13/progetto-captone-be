package capstone_project.project.controller;

import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.request.DettagliPagamentoRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.service.DettagliPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dettagli_pagamenti")
public class DettagliPagamentoController {

    @Autowired
    private DettagliPagamentoService dettagliPagamentoService;

    // Metodo per ottenere tutti i dettagli dei pagamenti paginati
    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllDettagliPagamenti(Pageable pageable) {
        return DefaultResponse.noMessage(dettagliPagamentoService.getAllDettagliPagamenti(pageable), HttpStatus.OK);
    }

    // Metodo per ottenere un singolo dettaglio del pagamento tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getDettagliPagamentoById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.noMessage(dettagliPagamentoService.getDettagliPagamentoById(id), HttpStatus.OK);
    }

    // Metodo per creare un nuovo dettaglio del pagamento
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createDettagliPagamento(@RequestBody @Validated DettagliPagamentoRequest dettagliPagamentoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Creazione del dettaglio del pagamento e restituzione della risposta HTTP
        return DefaultResponse.noMessage(dettagliPagamentoService.createDettagliPagamento(dettagliPagamentoRequest), HttpStatus.OK);
    }

    // Metodo per aggiornare un dettaglio del pagamento esistente
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateDettagliPagamento(@PathVariable int id, @RequestBody DettagliPagamentoRequest dettagliPagamentoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Aggiornamento del dettaglio del pagamento e restituzione della risposta HTTP
        return DefaultResponse.noMessage(dettagliPagamentoService.updateDettagliPagamento(id, dettagliPagamentoRequest), HttpStatus.OK);
    }

    // Metodo per eliminare un dettaglio del pagamento esistente
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteDettagliPagamento(@PathVariable int id) throws NotFoundException {
        // Eliminazione del dettaglio del pagamento e restituzione della risposta HTTP
        dettagliPagamentoService.deleteDettagliPagamento(id);
        return DefaultResponse.noObject("Il dettaglio con il pagamento con ID " + id + " è stata eliminata", HttpStatus.OK);
    }
}
