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
@RequestMapping("/informazioni_pagamenti")
public class InformazioniPagamentoController {

    @Autowired
    private InformazioniPagamentoService informazioniPagamentoService;

    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllInformazioniPagamenti(Pageable pageable){
        return DefaultResponse.noMessage(informazioniPagamentoService.getAllInformazioniPagamenti(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getInformazioniPagamentoById(@PathVariable int id)throws NotFoundException {
        return DefaultResponse.noMessage(informazioniPagamentoService.getInformazioniPagamentoById(id), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createInformazioniPagamento(@RequestBody @Validated InformazioniPagamentoRequest informazioniPagamentoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if (bindingResult.hasErrors()) throw  new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(informazioniPagamentoService.createInformazioniPagamento(informazioniPagamentoRequest), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateInformazioniPagamento(@PathVariable int id, @RequestBody InformazioniPagamentoRequest informazioniPagamentoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if (bindingResult.hasErrors()) throw new  BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(informazioniPagamentoService.updateInformazioniPagamento(id, informazioniPagamentoRequest), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteInformazioniPagamento(@PathVariable int id)throws NotFoundException{
        informazioniPagamentoService.deleteInformazioniPagamento(id);
        return DefaultResponse.noObject("L'informazione con il pagamento con ID " + id + " è stata eliminata", HttpStatus.OK);
    }
}
