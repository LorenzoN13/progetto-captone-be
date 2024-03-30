package capstone_project.project.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class UtenteRequest {

    // Nome utente
    @NotBlank(message = "Username obbligatorio")
    private String username;

    // Indirizzo email
    @NotBlank(message = "Email obbligatoria")
    @Email(message = "Deve essere un indirizzo email valido")
    private String email;

    // Nome dell'utente
    @NotBlank(message = "Nome obbligatorio")
    private String nome;

    // Cognome dell'utente
    @NotBlank(message = "Cognome obbligatorio")
    private String cognome;
}
