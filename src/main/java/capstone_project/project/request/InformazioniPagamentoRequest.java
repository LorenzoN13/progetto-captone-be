package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class InformazioniPagamentoRequest {

    // Nome del titolare della carta di pagamento
    @NotBlank(message = "Nome del titolare obbligatorio")
    private String NomeDelTitolare;

    // Numero della carta di pagamento
    @NotBlank(message = "Numero della carta obbligatorio")
    private String numeroCarta;

    // Data di scadenza della carta di pagamento
    @NotNull(message = "Data di scadenza obbligatoria")
    private LocalDate dataDiScadenza;

    // Codice CVV della carta di pagamento
    @NotBlank(message = "CVV obbligatorio")
    private String cvv;
}
