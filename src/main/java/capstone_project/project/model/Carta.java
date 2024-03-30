package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

// Annotazione per indicare che questa classe è un'entità gestita da JPA.
@Entity
// Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString.
@Data
// Specifica il nome della tabella nel database.
@Table(name = "carta")
public class Carta {
    // Annotazione per indicare che questo campo è la chiave primaria.
    @Id
    // Annotazione per specificare come viene generato il valore della chiave primaria (autoincrementale).
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Annotazione per indicare una relazione uno a uno con l'entità Utente.
    @OneToOne
    // Specifica la colonna nel database che contiene la chiave esterna verso l'entità Utente.
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    // Annotazione per indicare una relazione uno a molti con l'entità DettagliCarta.
    @OneToMany(mappedBy = "carta", cascade = CascadeType.ALL)
    // Insieme di dettagli associati a questa carta.
    private Set<DettagliCarta> dettaglicarta = new HashSet<>();

    // Campo per memorizzare il prezzo totale della carta.
    private double prezzoTotale;

    // Campo per memorizzare il numero totale di articoli nella carta.
    private int articoliTotali;

    // Campo per memorizzare il totale del prezzo scontato della carta.
    private int totalePrezzoScontato;

    // Campo per memorizzare l'ammontare dello sconto applicato alla carta.
    private int sconto;
}
