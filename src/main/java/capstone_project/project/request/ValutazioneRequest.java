package capstone_project.project.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValutazioneRequest {

    @NotNull(message = "valutazione obbligatoria")
    private double valutazione;

    private Integer idUtente;
    private Integer idProdotto;
}
