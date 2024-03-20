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
@RequestMapping("/valutazione")
public class ValutazioneController {

    @Autowired
    private ValutazioneService valutazioneService;

    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllValutazioni(Pageable pageable){
        return DefaultResponse.noMessage(valutazioneService.getAllValutazioni(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getValutazioneById(@PathVariable int id)throws NotFoundException {
        return DefaultResponse.noMessage(valutazioneService.getValutazioneById(id), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createValutazione(@RequestBody @Validated ValutazioneRequest valutazioneRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if (bindingResult.hasErrors()) throw  new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(valutazioneService.createValutazione(valutazioneRequest), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateValutazione(@PathVariable int id, @RequestBody ValutazioneRequest valutazioneRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if (bindingResult.hasErrors()) throw new  BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(valutazioneService.updateValutazione(id, valutazioneRequest), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteValutazione(@PathVariable int id)throws NotFoundException{
        valutazioneService.deleteValutazione(id);
        return DefaultResponse.noObject("La valutazione con ID " + id + " è stata eliminata", HttpStatus.OK);
    }
}
