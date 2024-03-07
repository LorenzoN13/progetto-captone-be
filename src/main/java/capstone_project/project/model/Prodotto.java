package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;

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
    private double prezzo;
    private int prezzoScontato;
    private int quantita;
    private String marchio;
    private String colore;
    private String immagineUrl;

    @OneToMany(mappedBy = "prodotto",cascade = CascadeType.ALL)
    private List<Valutazione> valutazioni;

    @Embedded
    @ManyToMany
    private Set<Dimensione>dimensioni = new HashSet<>();

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL)
    private List<Recensione>recensioni;

    private int numeroDiRecensioni;

    @ManyToOne
    @JoinColumn(name = "id_caetgoria")
    private Categoria categoria;
}
