package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "prodotto")
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String titolo;
    private String descrizione;
    private String brand;
    private double prezzo;
    private int prezzoScontato;
    private int quantita;
    private String marchio;
    private String colore;
    private String dimensione;
    @URL
    private String immagineUrl;
    private String categoria;

    @OneToMany(mappedBy = "prodotto",cascade = CascadeType.ALL)
    private List<Valutazione> valutazioni;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL)
    private List<Recensione>recensioni;

    private int numeroDiRecensioni;

}
