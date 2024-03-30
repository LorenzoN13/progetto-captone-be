package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;

// Annotazione per indicare che questa classe è un'entità gestita da JPA.
@Entity
// Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString.
@Data
// Specifica il nome della tabella nel database.
@Table(name = "dettagli_pagamento")
// Annotazione per indicare che questa classe può essere incorporata in un'altra entità.
@Embeddable
public class DettagliPagamento {
    // Campo per rappresentare l'identificatore univoco dei dettagli di pagamento.
    @Id
    private int id;

    // Campo per memorizzare il metodo di pagamento utilizzato.
    private String metodoPagamento;

    // Campo per memorizzare lo stato del pagamento.
    private String stato;
}
