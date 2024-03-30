package capstone_project.project.request;

import capstone_project.project.model.Ordine;
import capstone_project.project.model.Prodotto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class OrdineArticoloRequest {

    // Ordine associato all'articolo dell'ordine
    @NotNull(message = "Ordine obbligatorio")
    private Ordine ordine;

    // Prodotto associato all'articolo dell'ordine
    @NotNull(message = "Prodotto obbligatorio")
    private Prodotto prodotto;

    // Dimensione dell'articolo dell'ordine
    @NotBlank(message = "Dimensione obbligatoria")
    private String dimensione;

    // Quantità dell'articolo dell'ordine
    @NotNull(message = "Quantità obbligatoria")
    private int quantita;

    // Prezzo scontato dell'articolo dell'ordine
    @NotNull(message = "Prezzo scontato obbligatorio")
    private int prezzoScontato;

    // ID del prodotto associato all'articolo dell'ordine
    private Integer idProdotto;

    // ID dell'ordine associato all'articolo dell'ordine
    private Integer idOrdine;
}
