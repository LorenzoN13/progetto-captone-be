package capstone_project.project.request;

import capstone_project.project.model.DettagliCarta;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CartaRequest {

    @NotNull(message = "prezzo tolale obbligatorio")
    private double prezzoTotale;

    @NotNull(message = "articoli totali obbligatori")
    private int articoliTotali;

    @NotNull(message = "prezzo totale scontato obbligatorio")
    private int totalePrezzoScontato;

    @NotNull(message = "sconto obbligatorio")
    private int sconto;

    @NotNull(message = "non può essere null")
    private List<DettagliCartaRequest> dettagliCarta;



    private Integer idUtente;
}
