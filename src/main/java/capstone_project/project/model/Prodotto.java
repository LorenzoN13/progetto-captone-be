package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Annotazione per indicare che questa classe è un'entità gestita da JPA.
@Entity
// Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString.
@Data
// Specifica il nome della tabella nel database.
@Table(name = "prodotto")
public class Prodotto {

    // Campo per rappresentare l'identificatore univoco del prodotto.
    @Id
    // Annotazione per specificare come viene generato il valore dell'identificatore.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Campo per memorizzare il titolo del prodotto.
    private String titolo;

    // Campo per memorizzare la descrizione del prodotto.
    private String descrizione;

    // Campo per memorizzare il brand del prodotto.
    private String brand;

    // Campo per memorizzare il prezzo del prodotto.
    private double prezzo;

    // Campo per memorizzare il prezzo scontato del prodotto.
    private int prezzoScontato;

    // Campo per memorizzare la quantità disponibile del prodotto.
    private int quantita;

    // Campo per memorizzare il marchio del prodotto.
    private String marchio;

    // Campo per memorizzare il colore del prodotto.
    private String colore;

    // Campo per memorizzare la dimensione del prodotto.
    private String dimensione;

    // Campo per memorizzare l'URL dell'immagine del prodotto.
    @URL
    private String immagineUrl;

    // Campo per memorizzare la categoria del prodotto.
    private String categoria;

    // Campo per memorizzare la lista delle valutazioni associate al prodotto.
    @OneToMany(mappedBy = "prodotto",cascade = CascadeType.ALL)
    private List<Valutazione> valutazioni;

    // Campo per memorizzare la lista delle recensioni associate al prodotto.
    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL)
    private List<Recensione>recensioni;

    // Campo per memorizzare il numero totale di recensioni associate al prodotto.
    private int numeroDiRecensioni;
}
