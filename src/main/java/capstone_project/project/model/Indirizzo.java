package capstone_project.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

// Annotazione per indicare che questa classe è un'entità gestita da JPA.
@Entity
// Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString.
@Data
// Specifica il nome della tabella nel database.
@Table(name = "indirizzo")
public class Indirizzo {

    // Campo per rappresentare l'identificatore univoco dell'indirizzo.
    @Id
    // Annotazione per specificare come viene generato il valore dell'identificatore (autoincrementale).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Campo per memorizzare il nome della via.
    private String via;

    // Campo per memorizzare il numero civico.
    private String civico;

    // Campo per memorizzare il nome del comune.
    private String comune;

    // Campo per memorizzare il codice di avviamento postale (CAP).
    private String cap;

    // Annotazione per indicare una relazione molti a uno con l'entità Utente.
    @ManyToOne
    // Annotazione per specificare la colonna nel database che contiene la chiave esterna verso l'entità Utente.
    @JoinColumn(name = "id_cliente")
    // Annotazione per ignorare la serializzazione/deserializzazione di questo campo.
    @JsonIgnore
    private Utente utente;
}
