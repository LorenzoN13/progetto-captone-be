package capstone_project.project.request;

import capstone_project.project.model.Prodotto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class DettagliCartaRequest {

    // Prodotto associato ai dettagli della carta
    @NotNull(message = "Prodotto non può essere nullo")
    private Prodotto prodotto;

    // Dimensione del prodotto nella carta
    @NotBlank(message = "Dimensione obbligatoria")
    private String dimensione;

    // Quantità del prodotto nella carta
    @NotNull(message = "Quantità obbligatoria")
    private int quantita;

    // Prezzo del prodotto nella carta
    @NotNull(message = "Prezzo obbligatorio")
    private int prezzo;

    // Prezzo scontato del prodotto nella carta
    @NotNull(message = "Prezzo scontato obbligatorio")
    private int prezzoScontato;

    // ID del prodotto associato ai dettagli della carta
    private Integer idProdotto;
}
