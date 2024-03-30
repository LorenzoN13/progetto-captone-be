package capstone_project.project.request;

import capstone_project.project.model.DettagliPagamento;

import capstone_project.project.model.OrdineArticolo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

// Annotazione per generare automaticamente metodi standard come getter, setter, equals, hashCode e toString
@Data
public class OrdineRequest {

    // Data dell'ordine
    @NotNull(message = "Data ordine obbligatoria")
    private LocalDateTime dataOrdine;

    // Data di consegna dell'ordine
    @NotNull(message = "Data consegna obbligatoria")
    private LocalDateTime dataConsegna;

    // Prezzo totale dell'ordine
    @NotNull(message = "Prezzo totale obbligatorio")
    private double prezzoTotale;

    // Prezzo scontato totale dell'ordine
    @NotNull(message = "Prezzo scontato totale obbligatorio")
    private int PrezzoScontatoTotale;

    // Sconto applicato all'ordine
    @NotNull(message = "Sconto obbligatorio")
    private int sconto;

    // Stato dell'ordine
    @NotBlank(message = "Stato dell'ordine obbligatorio")
    private String statoOrdine;

    // Numero di articoli finali nell'ordine
    @NotNull(message = "Articolo finale obbligatorio")
    private int articoloFinale;

    // Dettagli del pagamento dell'ordine
    @NotNull(message = "Dettagli del pagamento obbligatori")
    private DettagliPagamento dettagliPagamento;

    // Lista di articoli dell'ordine
    @NotNull(message = "Ordine articoli obbligatori")
    private List<OrdineArticolo> ordineArticoli;

    // ID dell'utente associato all'ordine
    private Integer idUtente;

    // ID dell'indirizzo di spedizione associato all'ordine
    private Integer idIndirizzo;

    // ID dell'articolo dell'ordine associato all'ordine
    private Integer idOrdineArticolo;

    // ID del dettaglio del pagamento associato all'ordine
    private Integer idDettaglioPagamento;
}
