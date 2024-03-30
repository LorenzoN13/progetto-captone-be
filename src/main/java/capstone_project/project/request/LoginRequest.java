package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class LoginRequest {

    // Nome utente per il login
    @NotBlank
    private String username;

    // Password per il login
    @NotBlank
    private String password;
}
