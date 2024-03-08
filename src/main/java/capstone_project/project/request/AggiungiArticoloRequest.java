package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AggiungiArticoloRequest {
    @NotBlank(message = "dimesione obbligatoria")
    private String dimensione;
    @NotNull(message = "prezzo obbligatorio")
    private int prezzo;
    @NotNull(message = "quantita obbligatoria")
    private int quantita;
}
