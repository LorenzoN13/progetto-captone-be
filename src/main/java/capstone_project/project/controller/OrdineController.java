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
@RequestMapping("/ordini")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllOrdini(Pageable pageable){
        return DefaultResponse.noMessage(ordineService.getAllOrdini(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getOrdineById(@PathVariable int id)throws NotFoundException {
        return DefaultResponse.noMessage(ordineService.getOrdineById(id), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createOrdine(@RequestBody @Validated OrdineRequest ordineRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if (bindingResult.hasErrors()) throw  new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(ordineService.createOrdine(ordineRequest), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateOrdine(@PathVariable int id, @RequestBody OrdineRequest ordineRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if (bindingResult.hasErrors()) throw new  BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(ordineService.updateOrdine(id, ordineRequest), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteOrdine(@PathVariable int id)throws NotFoundException{
        ordineService.deleteOrdine(id);
        return DefaultResponse.noObject("L'ordine con ID " + id + " è stato eliminato", HttpStatus.OK);
    }
}
