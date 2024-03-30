package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Annotazione per indicare che questa classe è un'entità gestita da JPA.
@Entity
// Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString.
@Data
// Specifica il nome della tabella nel database.
@Table(name = "ordine")
public class Ordine {

    // Campo per rappresentare l'identificatore univoco dell'ordine.
    @Id
    // Annotazione per specificare come viene generato il valore dell'identificatore (autoincrementale).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Campo per indicare l'utente che ha effettuato l'ordine.
    @ManyToOne
    private Utente utente;

    // Campo per memorizzare la data e l'ora dell'ordine.
    private LocalDateTime dataOrdine;

    // Campo per memorizzare la data e l'ora di consegna prevista dell'ordine.
    private LocalDateTime dataConsegna;

    // Campo per memorizzare il prezzo totale dell'ordine.
    private double prezzoTotale;

    // Campo per memorizzare il prezzo totale scontato dell'ordine.
    private int prezzoScontatoTotale;

    // Campo per memorizzare l'ammontare dello sconto applicato all'ordine.
    private int sconto;

    // Campo per memorizzare lo stato dell'ordine.
    private String statoOrdine;

    // Campo per memorizzare il numero totale di articoli nell'ordine.
    private int articoloFinale;

    // Campo per memorizzare i dettagli di pagamento dell'ordine.
    @Embedded
    private DettagliPagamento dettagliPagamento;

    // Campo per memorizzare l'indirizzo di spedizione dell'ordine.
    @OneToOne
    private Indirizzo indirizzoDiSpedizione;

    // Campo per memorizzare la lista degli articoli presenti nell'ordine.
    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<OrdineArticolo> ordineArticoli = new ArrayList<>();
}
