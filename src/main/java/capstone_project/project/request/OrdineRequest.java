package capstone_project.project.request;

import capstone_project.project.model.DettagliPagamento;

import capstone_project.project.model.OrdineArticolo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdineRequest {

    @NotNull(message = "data ordine obbligatorio")
    private LocalDateTime dataOrdine;

    @NotNull(message = "data consegna obbligatoria")
    private LocalDateTime dataConsegna;

    @NotNull(message = "prezzo Totale obbligatorio")
    private double prezzoTotale;

    @NotNull(message = "PrezzoScontatoTotale obbligatorio")
    private int PrezzoScontatoTotale;

    @NotNull(message = "sconto obbligatorio")
    private int sconto;

    @NotBlank(message = "stato dell'ordine obbligatorio")
    private String statoOrdine;

    @NotNull(message = "articoloFinale obbligatorio")
    private int articoloFinale;

    @NotNull(message = "dettagli del Pagamento obbligatori")
    private DettagliPagamento dettagliPagamento;

    @NotNull(message = "ordineArticoli obbligatori")
    private List<OrdineArticolo> ordineArticoli;

    private Integer idUtente;

    private Integer idIndirizzo;

    private Integer idOrdineArticolo;
}
