package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecensioneRequest {

    @NotBlank(message = "recensione obbligatoria")
    private String recensione;

    private Integer idUtente;
    private Integer idProdotto;

}
