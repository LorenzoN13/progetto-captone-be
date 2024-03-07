package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DimensioneRequest {

    @NotBlank(message = "nome della dimensione obbligatorio")
    private String nome;
    @NotNull(message = "quantità obbligatoria")
    private int quantita;
}
