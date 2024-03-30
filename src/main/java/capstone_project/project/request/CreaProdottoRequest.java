package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class CreaProdottoRequest {

    // Titolo del prodotto
    @NotBlank(message = "Titolo obbligatorio")
    private String titolo;

    // Descrizione del prodotto
    @NotBlank(message = "Descrizione obbligatoria")
    private String descrizione;

    // Prezzo del prodotto
    @NotNull(message = "Prezzo obbligatorio")
    private int prezzo;

    // Prezzo scontato del prodotto
    @NotNull(message = "Prezzo scontato obbligatorio")
    private int prezzoScontato;

    // Percentuale di sconto applicata al prezzo del prodotto
    @NotNull(message = "Percentuale di sconto obbligatoria")
    private int percentualeSconto;

    // Brand del prodotto
    @NotBlank(message = "Brand obbligatorio")
    private String brand;

    // Colore del prodotto
    @NotBlank(message = "Colore obbligatorio")
    private String colore;

    // Dimensione del prodotto
    @NotBlank(message = "Dimensione obbligatoria")
    private String dimensione;

    // URL dell'immagine del prodotto
    @NotBlank(message = "URL dell'immagine obbligatorio")
    private String immagineUrl;

    // Categoria del prodotto
    @NotBlank(message = "Categoria obbligatoria")
    private String categoria;

    // Quantità disponibile del prodotto
    @NotNull(message = "Quantità obbligatoria")
    private int quantita;
}
