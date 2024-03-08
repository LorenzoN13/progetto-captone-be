package capstone_project.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ordine_articolo")
public class OrdineArticolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne
    private Ordine ordine;

    @ManyToOne
    private Prodotto prodotto;

    private String dimensione;

    private int quantita;

    private int prezzoScontato;

}
