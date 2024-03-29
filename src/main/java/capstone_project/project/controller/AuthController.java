package capstone_project.project.controller;

import capstone_project.project.exception.BadRequestExceptionHandler;
import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.Utente;
import capstone_project.project.request.LoginRequest;
import capstone_project.project.request.RegisterRequest;
import capstone_project.project.request.SendMessageRequest;
import capstone_project.project.responses.DefaultResponse;
import capstone_project.project.responses.LoginResponse;
import capstone_project.project.security.JwtTools;
import capstone_project.project.service.UtenteService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired //inietta il JavaMailSender all'intern del controller
    private JavaMailSenderImpl mailSender;

    @Autowired//inietta l' UtenteService all'intern del controller
    private UtenteService utenteService;

    @Autowired//inietta il PasswordEncoder all'intern del controller
    private PasswordEncoder encoder;

    @Autowired//inietta il JwtTools all'intern del controller
    private JwtTools jwtTools;

    @PostMapping("/auth/register")
    public ResponseEntity<DefaultResponse> register(@RequestBody @Validated RegisterRequest registerRequest, BindingResult bindingResult) throws BadRequestExceptionHandler {
        // Gestione degli errori di validazione
        if(bindingResult.hasErrors()){
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }

        // Invia un'email di ringraziamento all'utente registrato
        sendEmail(registerRequest.getEmail());

        // Salva l'utente nel sistema e restituisce una risposta HTTP con lo stato "CREATED"
        return DefaultResponse.noMessage(utenteService.saveUtente(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult) throws BadRequestExceptionHandler, NotFoundException {
        // Gestione degli errori di validazione
        if(bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        // Recupera l'utente dal servizio utente tramite il nome utente fornito nella richiesta
        Utente utente = utenteService.findByUsername(loginRequest.getUsername());

        // Verifica se la password fornita corrisponde alla password dell'utente
        if(!encoder.matches(loginRequest.getPassword(), utente.getPassword()))
            throw new BadRequestExceptionHandler("Password errata");

        // Crea un token JWT per l'utente e restituisce una risposta HTTP con il token e i dettagli dell'utente
        String token = jwtTools.createToken(utente);
        return LoginResponse.login(token, utente, HttpStatus.OK);
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<DefaultResponse> sendEmailToContact(@RequestBody SendMessageRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        // Estrae il nome utente dell'amministratore dal token di autorizzazione
        String adminUsername = extractAdminUsernameFromToken(auth);

        // Invia un'email al destinatario utilizzando le informazioni fornite nella richiesta
        sendEmailFromAdmin(request.getDestinatario(), request.getOggetto(), request.getMessaggio(), adminUsername);

        // Restituisce una risposta HTTP con uno stato "OK"
        return DefaultResponse.noMessage("Email sent successfully", HttpStatus.OK);
    }

    // Metodo per estrarre il nome utente dell'amministratore dal token JWT
    private String extractAdminUsernameFromToken(String authHeader) {
        String token = authHeader.substring(7);
        return jwtTools.extractUsername(token);
    }

    // Metodo per inviare un'email dall'amministratore al destinatario
    private void sendEmailFromAdmin(String recipientEmail, String subject, String message, String adminUsername) {
        // Costruisce il messaggio email con il nome utente dell'amministratore incluso nel corpo del messaggio
        String messageWithAdminUsername = message + "\n\nAdmin: " + adminUsername;

        // Crea un oggetto SimpleMailMessage e imposta i dettagli del messaggio
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(messageWithAdminUsername);

        // Invia l'email utilizzando il JavaMailSender
        mailSender.send(simpleMailMessage);
    }

    // Metodo per inviare un'email di ringraziamento all'utente registrato
    private void sendEmail(String email) {
        // Costruisce il messaggio email di ringraziamento
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Thank you for subscribe");
        simpleMailMessage.setText("Thank you very GRAZIE!");

        // Invia l'email utilizzando il JavaMailSender
        mailSender.send(simpleMailMessage);
    }
}
