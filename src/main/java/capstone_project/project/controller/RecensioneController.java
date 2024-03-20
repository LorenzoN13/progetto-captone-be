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
@RequestMapping("/recensioni")
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;

    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllRecensioni(Pageable pageable){
        return DefaultResponse.noMessage(recensioneService.getAllRecensioni(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getRecensioneById(@PathVariable int id)throws NotFoundException {
        return DefaultResponse.noMessage(recensioneService.getRecensioneById(id), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createRecensione(@RequestBody @Validated RecensioneRequest recensioneRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if (bindingResult.hasErrors()) throw  new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(recensioneService.createRecensione(recensioneRequest), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateRecensione(@PathVariable int id, @RequestBody RecensioneRequest recensioneRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if (bindingResult.hasErrors()) throw new  BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(recensioneService.updateRecensione(id, recensioneRequest), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteRecensione(@PathVariable int id)throws NotFoundException{
        recensioneService.deleteRecensione(id);
        return DefaultResponse.noObject("La recensione con ID " + id + " è stata eliminata", HttpStatus.OK);
    }
}
