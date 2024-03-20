package capstone_project.project.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InformazioniPagamentoRequest {
    @NotBlank(message = "nome del titolare obbligatorio")
    private String NomeDelTitolare;

    @NotBlank(message = "numero della carta obbligatorio")
    private String numeroCarta;

    @NotNull(message = "data della scadenza obbligatoria")
    private LocalDate dataDiScadenza;

    @NotBlank(message = "cvv obbligatorio")
    private String cvv;

}
