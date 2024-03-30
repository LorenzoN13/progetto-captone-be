package capstone_project.project.request;

import capstone_project.project.model.DettagliCarta;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
@Component
public class CartaRequest {

    // Prezzo totale della carta
    @NotNull(message = "Prezzo totale obbligatorio")
    private double prezzoTotale;

    // Numero totale di articoli nella carta
    @NotNull(message = "Articoli totali obbligatori")
    private int articoliTotali;

    // Prezzo totale scontato della carta
    @NotNull(message = "Prezzo totale scontato obbligatorio")
    private int totalePrezzoScontato;

    // Percentuale di sconto applicata alla carta
    @NotNull(message = "Sconto obbligatorio")
    private int sconto;

    // Dettagli della carta, rappresentati come una lista di richieste DettagliCartaRequest
    @NotNull(message = "Dettagli carta non possono essere nulli")
    private List<DettagliCartaRequest> dettagliCarta;

    // ID dell'utente associato alla carta
    private Integer idUtente;
}
