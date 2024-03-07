package capstone_project.project.model;

import capstone_project.project.Enum.Ruolo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String cognome;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String username;

    private String avatar;

    @Column(unique = true)
    private String numero;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Indirizzo> indirizzi;


    @Embedded
    @ManyToMany
    private List<InformazioniPagamento> informazioniPagamenti;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Valutazione> valutazioni;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Recensione> recensioni;
}
