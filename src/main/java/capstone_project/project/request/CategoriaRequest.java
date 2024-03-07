package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaRequest {

    @NotBlank(message = "nome della categoria obbliogatorio")
    private String nome;

    @NotNull(message = "livello obbligatorio")
    private int livello;
}
