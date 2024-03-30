package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class AggiungiArticoloRequest {
    // Dimensione dell'articolo
    @NotBlank(message = "Dimensione obbligatoria")
    private String dimensione;

    // Prezzo dell'articolo
    @NotNull(message = "Prezzo obbligatorio")
    private int prezzo;

    // Quantità dell'articolo
    @NotNull(message = "Quantità obbligatoria")
    private int quantita;
}
