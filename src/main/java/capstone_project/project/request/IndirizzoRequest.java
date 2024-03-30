package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
@Component
public class IndirizzoRequest {

    // Nome della via
    @NotBlank(message = "Via obbligatoria")
    private String via;

    // Numero civico
    @NotBlank(message = "Numero civico obbligatorio")
    private String civico;

    // Nome del comune
    @NotBlank(message = "Comune obbligatorio")
    private String comune;

    // Codice di avviamento postale (CAP)
    @NotBlank(message = "CAP obbligatorio")
    private String cap;

    // ID dell'utente associato all'indirizzo
    private Integer idUtente;
}
