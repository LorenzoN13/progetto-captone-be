package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class DettagliPagamentoRequest {

    // Metodo di pagamento associato ai dettagli del pagamento
    @NotBlank(message = "Metodo di pagamento obbligatorio")
    private String metodoPagamento;

    // Stato associato ai dettagli del pagamento
    @NotBlank(message = "Stato obbligatorio")
    private String stato;
}
