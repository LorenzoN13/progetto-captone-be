package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "ordine")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Utente utente;

    private LocalDateTime dataOrdine;

    private LocalDateTime dataConsegna;

    private double prezzoTotale;

    private int PrezzoScontatoTotale;

    private int scontato;

    private String statoOrdine;

    private int articoloFinale;

    @Embedded
    private DettagliPagamento dettagliPagamento = new DettagliPagamento();

    @OneToOne
    private Indirizzo indirizzoDiSpedizione;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<OrdineArticolo> ordineArticoli;


}
