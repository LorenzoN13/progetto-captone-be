package capstone_project.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

// Annotazione per indicare che questa classe è un'entità gestita da JPA.
@Entity
// Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString.
@Data
// Specifica il nome della tabella nel database.
@Table(name = "ordine_articolo")
public class OrdineArticolo {

    // Campo per rappresentare l'identificatore univoco dell'ordine articolo.
    @Id
    // Annotazione per specificare come viene generato il valore dell'identificatore (autoincrementale).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Campo per indicare l'ordine a cui appartiene questo articolo.
    @JsonIgnore
    // Annotazione per indicare una relazione molti a uno con l'entità Ordine.
    @ManyToOne
    private Ordine ordine;

    // Campo per indicare il prodotto associato a questo articolo.
    @ManyToOne
    private Prodotto prodotto;

    // Campo per memorizzare la dimensione del prodotto.
    private String dimensione;

    // Campo per memorizzare la quantità di questo prodotto nell'ordine.
    private int quantita;

    // Campo per memorizzare il prezzo scontato di questo prodotto nell'ordine.
    private int prezzoScontato;

}
