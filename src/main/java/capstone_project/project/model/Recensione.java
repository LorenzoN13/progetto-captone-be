package capstone_project.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

// Annotazione per indicare che questa classe è un'entità gestita da JPA.
@Entity
// Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString.
@Data
// Specifica il nome della tabella nel database.
@Table(name = "recensione")
public class Recensione {

    // Campo per rappresentare l'identificatore univoco della recensione.
    @Id
    // Annotazione per specificare come viene generato il valore dell'identificatore (autoincrementale).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Campo per memorizzare il testo della recensione.
    private String recensione;

    // Campo per indicare l'utente che ha scritto la recensione.
    @ManyToOne
    // Annotazione per specificare la colonna nel database che contiene la chiave esterna verso l'entità Utente.
    @JoinColumn(name = "id_utente")
    private Utente utente;

    // Campo per indicare il prodotto a cui è associata la recensione.
    @ManyToOne
    // Annotazione per ignorare la serializzazione/deserializzazione di questo campo.
    @JsonIgnore
    // Annotazione per specificare la colonna nel database che contiene la chiave esterna verso l'entità Prodotto.
    @JoinColumn(name = "id_prodotto")
    private Prodotto prodotto;
}
