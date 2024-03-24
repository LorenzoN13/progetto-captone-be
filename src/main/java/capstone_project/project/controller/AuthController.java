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
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtTools jwtTools;
    @PostMapping("/register")
    public ResponseEntity<DefaultResponse> register(@RequestBody @Validated RegisterRequest registerRequest, BindingResult bindingResult) throws BadRequestExceptionHandler {
        if(bindingResult.hasErrors()){
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }

        sendEmail(registerRequest.getEmail());
        return DefaultResponse.noMessage(utenteService.saveUtente(registerRequest), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult) throws BadRequestExceptionHandler, NotFoundException {
        if(bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        Utente utente = utenteService.findByUsername(loginRequest.getUsername());
        if(!encoder.matches(loginRequest.getPassword(), utente.getPassword())) throw new BadRequestExceptionHandler("Password errata");
        String token= jwtTools.createToken(utente);
        return LoginResponse.login(token,utente,HttpStatus.OK);
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<DefaultResponse> sendEmailToContact(@RequestBody SendMessageRequest request,
                                                              @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String adminUsername = extractAdminUsernameFromToken(auth);
        sendEmailFromAdmin(request.getDestinatario(), request.getOggetto(), request.getMessaggio(), adminUsername);
        return DefaultResponse.noMessage("Email sent successfully", HttpStatus.OK);
    }

    private String extractAdminUsernameFromToken(String authHeader) {
        String token = authHeader.substring(7);
        return jwtTools.extractUsername(token);
    }

    private void sendEmailFromAdmin(String recipientEmail, String subject, String message, String adminUsername) {
        String messageWithAdminUsername = message + "\n\nAdmin: " + adminUsername;

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(messageWithAdminUsername);

        mailSender.send(simpleMailMessage);
    }

    private void sendEmail(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Thank you for subscribe");
        simpleMailMessage.setText("Thank you very GRAZIE!");
        mailSender.send(simpleMailMessage);
    }
}
