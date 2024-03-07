package capstone_project.project.responses;

import capstone_project.project.model.Utente;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
public class LoginResponse {
    private LocalDateTime loginDate;
    private String token;
    private Utente utente;

    public LoginResponse(String token, Utente utente) {
        this.token = token;
        this.utente = utente;
        loginDate=LocalDateTime.now();
    }
    public static ResponseEntity<LoginResponse> login(String token, Utente utente, HttpStatus httpStatus){
        LoginResponse loginResponse=new LoginResponse(token,utente);
        return new ResponseEntity<>(loginResponse, httpStatus);
    }
}
