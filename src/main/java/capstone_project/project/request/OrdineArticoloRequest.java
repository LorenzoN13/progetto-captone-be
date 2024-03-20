package capstone_project.project.request;

import capstone_project.project.model.Ordine;
import capstone_project.project.model.Prodotto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdineArticoloRequest {
    @NotNull(message = "ordine obbligatorio")
    private Ordine ordine;

    @NotNull(message = "prodotto obbligatorio")
    private Prodotto prodotto;

    @NotBlank(message = "dimensione obbligatoria")
    private String dimensione;

    @NotNull(message = "quantita obbligatoria")
    private int quantita;

    @NotNull(message = "prezzo scontato obbligatorio")
    private int prezzoScontato;

    private Integer idProdotto;
    private Integer idOrdine;
}
