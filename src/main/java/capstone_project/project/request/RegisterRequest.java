package capstone_project.project.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class RegisterRequest {

    // Nome utente
    @NotBlank(message = "Username obbligatorio")
    private String username;

    // Indirizzo email
    @NotBlank(message = "Email obbligatoria")
    @Email(message = "Deve essere un indirizzo email valido")
    private String email;

    // Password
    @NotBlank(message = "Password obbligatoria")
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>.]).{8,}$",
            message = "La password deve contenere almeno:\n- 1 lettera maiuscola\n- 1 lettera minuscola\n- 1 numero\n- 1 carattere speciale\n- Minimo 8 caratteri")
    private String password;

    // Nome dell'utente
    @NotBlank(message = "Nome obbligatorio")
    private String nome;

    // Cognome dell'utente
    @NotBlank(message = "Cognome obbligatorio")
    private String cognome;
}
