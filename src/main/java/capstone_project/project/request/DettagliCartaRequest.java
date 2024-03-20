package capstone_project.project.request;

import capstone_project.project.model.Prodotto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DettagliCartaRequest {
    @NotNull(message = "non puo essere null")
    private Prodotto prodotto;

    @NotBlank(message = "dimnesione obbligatoria")
    private String dimensione;

    @NotNull(message = "quantit Obbligatoria")
    private int quantita;

    @NotNull(message = "prezzo obbligatorio")
    private int prezzo;

    @NotNull(message = "prezzo scontato")
    private int prezzoScontato;

    private Integer idProdotto;
}
