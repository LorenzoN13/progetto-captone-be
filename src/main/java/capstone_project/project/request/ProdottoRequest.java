package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ProdottoRequest {
    @NotBlank(message = "titolo obbligatorio")
    private String titolo;

    @NotBlank(message = "descrizione obbligatorio")
    private String descrizione;

    @NotNull(message = "prezzo obbligatorio")
    private double prezzo;

    @NotNull(message = "prezzo scontato obbligatorio")
    private int prezzoScontato;

    @NotNull(message = "quantita obbligatoria")
    private int quantita;

    @NotBlank(message = "marchio obbligatorio")
    private String marchio;

    @NotBlank(message = "colore obbligatorio")
    private String colore;

    @NotBlank(message = "immagine obbligatorio")
    private String immagineUrl;
}
