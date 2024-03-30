package capstone_project.project.responses;

import capstone_project.project.model.Utente;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class LoginResponse {
    // Data e ora del login
    private LocalDateTime loginDate;

    // Token di autenticazione
    private String token;

    // Oggetto Utente associato al login
    private Utente obj;

    // Costruttore che imposta il token e l'oggetto Utente e registra la data e l'ora del login
    public LoginResponse(String token, Utente utente) {
        this.token = token;
        this.obj = utente;
        loginDate = LocalDateTime.now();
    }

    // Metodo statico per creare una risposta di login, che restituisce un oggetto ResponseEntity
    public static ResponseEntity<LoginResponse> login(String token, Utente obj, HttpStatus httpStatus){
        // Crea un'istanza di LoginResponse
        LoginResponse loginResponse = new LoginResponse(token,obj);

        // Restituisce un'entità ResponseEntity con l'oggetto LoginResponse e lo stato HTTP specificato
        return new ResponseEntity<>(loginResponse, httpStatus);
    }
}
