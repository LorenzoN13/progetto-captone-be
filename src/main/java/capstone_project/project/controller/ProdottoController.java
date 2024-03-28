package capstone_project.project.controller;

import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.request.CreaProdottoRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllProdotti(Pageable pageable){
        return DefaultResponse.noMessage(prodottoService.getAllProdotti(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getProdottoById(@PathVariable int id)throws NotFoundException {
        return DefaultResponse.noMessage(prodottoService.getProdottoById(id), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createProdotto(@RequestBody @Validated CreaProdottoRequest creaProdottoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        System.out.println(creaProdottoRequest);
        if (bindingResult.hasErrors()) throw  new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(prodottoService.createProdotto(creaProdottoRequest), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateProdotto(@PathVariable int id, @RequestBody CreaProdottoRequest creaProdottoRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if (bindingResult.hasErrors()) throw new  BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(prodottoService.updateProdotto(id, creaProdottoRequest), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteProdotto(@PathVariable int id)throws NotFoundException{
        prodottoService.deleteProdotto(id);
        return DefaultResponse.noObject("Il prodotto con ID " + id + " è stato eliminato", HttpStatus.OK);
    }
}
