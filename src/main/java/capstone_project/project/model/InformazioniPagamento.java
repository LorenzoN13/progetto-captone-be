package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

// Annotazione per indicare che questa classe è un'entità gestita da JPA.
@Entity
// Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString.
@Data
// Specifica il nome della tabella nel database.
@Table(name = "informazioniPagamento")
public class InformazioniPagamento {

    // Campo per rappresentare l'identificatore univoco delle informazioni di pagamento.
    @Id
    // Annotazione per specificare come viene generato il valore dell'identificatore.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Campo per memorizzare il nome del titolare della carta di pagamento.
    private String nomeDelTitolare;

    // Campo per memorizzare il numero della carta di pagamento.
    @Column(name = "numero_carta", unique = true)
    private String numeroCarta;

    // Campo per memorizzare la data di scadenza della carta di pagamento.
    private LocalDate dataDiScadenza;

    // Campo per memorizzare il codice di sicurezza (CVV) della carta di pagamento.
    private String cvv;

}
