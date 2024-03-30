package capstone_project.project.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class ValutazioneRequest {

    // Valutazione dell'utente sul prodotto
    @NotNull(message = "Valutazione obbligatoria")
    private double valutazione;

    // ID dell'utente associato alla valutazione
    private Integer idUtente;

    // ID del prodotto associato alla valutazione
    private Integer idProdotto;
}
