package capstone_project.project.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "username obbligatorio")
    private String username;

    @NotBlank(message = "email obbligatoria")
    @Email
    private String email;

    @NotBlank(message = "password obbligatoria")
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>.]).{8,}$",
            message = "Password must contain:\n-1 letter uppercase\n-1 letter lowercase\n-1 number\n1 special character\n-Min 8 char")
    private String password;

    @NotBlank(message = "nome obbligatorio")
    private String nome;

    @NotBlank(message = "cognome obbligatorio")
    private String cognome;
}
