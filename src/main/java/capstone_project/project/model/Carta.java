package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "carta")
public class Carta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    @OneToMany(mappedBy = "carta", cascade = CascadeType.ALL)
    private Set<DettagliCarta> dettaglicarta = new HashSet<>();

    private double prezzoTotale;

    private int articoliTotali;

    private int totalePrezzoScontato;

    private int sconto;
}
