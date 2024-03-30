package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class RecensioneRequest {

    // Contenuto della recensione
    @NotBlank(message = "Recensione obbligatoria")
    private String recensione;

    // ID dell'utente associato alla recensione
    private Integer idUtente;

    // ID del prodotto associato alla recensione
    private Integer idProdotto;
}
