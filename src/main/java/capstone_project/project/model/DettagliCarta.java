package capstone_project.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

// Annotazione per indicare che questa classe è un'entità gestita da JPA.
@Entity
// Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString.
@Data
// Specifica il nome della tabella nel database.
@Table(name = "dettagli_carta")
public class DettagliCarta {
    // Annotazione per indicare che questo campo è la chiave primaria.
    @Id
    // Annotazione per specificare come viene generato il valore della chiave primaria (autoincrementale).
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Annotazione per ignorare la serializzazione/deserializzazione di questo campo.
    @JsonIgnore
    // Annotazione per indicare una relazione molti a uno con l'entità Carta.
    @ManyToOne
    private Carta carta;

    // Annotazione per indicare una relazione molti a uno con l'entità Prodotto.
    @ManyToOne
    private Prodotto prodotto;

    // Campo per memorizzare la dimensione del prodotto nei dettagli della carta.
    private String dimensione;

    // Campo per memorizzare la quantità del prodotto nei dettagli della carta.
    private int quantita;

    // Campo per memorizzare il prezzo unitario del prodotto nei dettagli della carta.
    private int prezzo;

    // Campo per memorizzare il prezzo unitario scontato del prodotto nei dettagli della carta.
    private int prezzoScontato;
}
