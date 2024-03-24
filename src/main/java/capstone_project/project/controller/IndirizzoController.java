package capstone_project.project.controller;

import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.request.DettagliPagamentoRequest;
import capstone_project.project.request.IndirizzoRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.service.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/indirizzi")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllIndirizzi(Pageable pageable){
        return DefaultResponse.noMessage(indirizzoService.getAllIndirizzi(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getIndirizzoById(@PathVariable int id)throws NotFoundException {
        return DefaultResponse.noMessage(indirizzoService.getIndirizzoById(id), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createIndirizzo(@RequestBody @Validated IndirizzoRequest indirizzoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if (bindingResult.hasErrors()) throw  new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(indirizzoService.createIndirizzo(indirizzoRequest), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateIndirizzo(@PathVariable int id, @RequestBody IndirizzoRequest indirizzoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if (bindingResult.hasErrors()) throw new  BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(indirizzoService.updateIndirizzo(id, indirizzoRequest), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteIndirizzo(@PathVariable int id)throws NotFoundException{
        indirizzoService.deleteIndirizzo(id);
        return DefaultResponse.noObject("L'indirizzo con ID " + id + " è stato eliminato", HttpStatus.OK);
    }
}
