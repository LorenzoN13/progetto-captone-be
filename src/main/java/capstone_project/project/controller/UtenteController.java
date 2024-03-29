package capstone_project.project.controller;

import capstone_project.project.exception.AlreadyAdminException;
import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Utente;
import capstone_project.project.request.RegisterRequest;
import capstone_project.project.request.UtenteRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.service.UtenteService;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private Cloudinary cloudinary;

    // Metodo per ottenere tutti gli utenti paginati
    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAllUtenti(Pageable pageable) {
        return DefaultResponse.noMessage(utenteService.getAllUtente(pageable), HttpStatus.OK);
    }

    // Metodo per ottenere un singolo utente tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getUtenteById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.noMessage(utenteService.getUtenteById(id), HttpStatus.OK);
    }

    // Metodo per creare un nuovo utente
    @PostMapping("")
    public ResponseEntity<DefaultResponse> createUtente(@RequestBody @Validated RegisterRequest registerRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Creazione dell'utente e restituzione della risposta HTTP
        return DefaultResponse.noMessage(utenteService.saveUtente(registerRequest), HttpStatus.OK);
    }

    // Metodo per aggiornare un utente esistente
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateUtente(@PathVariable int id, @RequestBody UtenteRequest utenteRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if (bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Aggiornamento dell'utente e restituzione della risposta HTTP
        return DefaultResponse.noMessage(utenteService.updateUtente(id, utenteRequest), HttpStatus.OK);
    }

    // Metodo per eliminare un utente esistente
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteUtente(@PathVariable int id) throws NotFoundException {
        // Eliminazione dell'utente e restituzione della risposta HTTP
        utenteService.deleteUtente(id);
        return DefaultResponse.noObject("Gli utenti con ID " + id + " sono stati eliminati", HttpStatus.OK);
    }

    // Metodo per caricare un avatar per un utente
    @PatchMapping("/{id}/upload")
    public ResponseEntity<DefaultResponse> uploadAvatar(@PathVariable int id, @RequestParam("upload") MultipartFile file) throws IOException, NotFoundException {
        // Caricamento dell'avatar utilizzando Cloudinary e aggiornamento dell'utente con il nuovo URL dell'avatar
        Utente utente = utenteService.uploadAvatar(id, (String)cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));
        return DefaultResponse.full("Avatar è stato caricato con successo", utente, HttpStatus.OK);
    }

    // Metodo per promuovere un utente a amministratore
    @PutMapping("/{Id}/promoteToAdmin")
    public ResponseEntity<DefaultResponse> promoteUtenteToAdmin(@PathVariable int Id) throws NotFoundException, AlreadyAdminException {
        // Promozione dell'utente a amministratore e restituzione della risposta HTTP
        utenteService.updateUtenteToAdmin(Id);
        return DefaultResponse.noObject("Utente promosso con successo al ruolo di amministratore.", HttpStatus.OK);
    }
}
