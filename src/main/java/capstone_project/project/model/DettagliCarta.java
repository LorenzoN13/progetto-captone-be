package capstone_project.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "dettagli_carta")
public class DettagliCarta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnore
    @ManyToOne
    private Carta carta;

    @ManyToOne
    private Prodotto prodotto;

    private String dimensione;

    private int quantita;

    private int prezzo;

    private int prezzoScontato;


}
