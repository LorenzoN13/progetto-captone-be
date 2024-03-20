package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DettagliPagamentoRequest {

    @NotBlank(message = "metodo pagamento obbligatorio")
    private String metodoPagamento;

    @NotBlank(message = "obbligatorio obbligatorio")
    private String stato;
}
