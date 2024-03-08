package capstone_project.project.request;

import capstone_project.project.model.Dimensione;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CreaProdottoRequest {

    @NotBlank(message = "titolo obbligatorio")
    private String titolo;

    @NotBlank(message = "descrizione obbligatorio")
    private String descrizione;

    @NotNull(message = "prezzo obbligatorio")
    private int prezzo;

    @NotNull(message = "prezzoScontato obbligatorio")
    private int prezzoScontato;

    @NotNull(message = "percentualeSconto obbligatorio")
    private int percentualeSconto;

    @NotBlank(message = "brand obbligatorio")
    private String brand;

    @NotBlank(message = "colore obligatorio")
    private String colore;

    @NotNull(message = "dimensione obbliagatoria")
    private Set<Dimensione> dimensioni = new HashSet<>();

    @NotBlank(message = "immagine obbligatoria")
    private String immagineUrl;

    @NotBlank(message = "categoria obbligatoria")
    private String categoria;
}
