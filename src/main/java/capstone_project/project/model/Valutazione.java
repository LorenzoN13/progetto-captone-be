package capstone_project.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

// Annotazione per indicare che questa classe è un'entità gestita da JPA.
@Entity
// Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString.
@Data
// Specifica il nome della tabella nel database.
@Table(name = "valutazione")
public class Valutazione {

    // Campo per rappresentare l'identificatore univoco della valutazione.
    @Id
    // Annotazione per specificare come viene generato il valore dell'identificatore (autoincrementale).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Campo per indicare l'utente che ha effettuato la valutazione.
    @ManyToOne
    // Annotazione per specificare la colonna nel database che contiene la chiave esterna verso l'entità Utente.
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    // Campo per indicare il prodotto valutato.
    @JsonIgnore
    @ManyToOne
    // Annotazione per specificare la colonna nel database che contiene la chiave esterna verso l'entità Prodotto.
    @JoinColumn(name = "id_prodotto", nullable = false)
    private Prodotto prodotto;

    // Campo per memorizzare il valore della valutazione.
    private double valutazione;
}
