package capstone_project.project.model;

import capstone_project.project.Enum.Ruolo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

// Annotazione per indicare che questa classe è un'entità gestita da JPA.
@Entity
// Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString.
@Data
// Specifica il nome della tabella nel database.
@Table(name = "utenti")
public class Utente implements UserDetails {

    // Campo per rappresentare l'identificatore univoco dell'utente.
    @Id
    // Annotazione per specificare come viene generato il valore dell'identificatore (autoincrementale).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Campo per memorizzare il nome dell'utente.
    private String nome;

    // Campo per memorizzare il cognome dell'utente.
    private String cognome;

    // Campo per memorizzare i ruoli assegnati all'utente.
    @Enumerated(EnumType.STRING)
    private Set<Ruolo> ruoli;

    // Campo per memorizzare l'email dell'utente.
    @Column(unique = true)
    private String email;

    // Campo per memorizzare la password dell'utente.
    private String password;

    // Campo per memorizzare lo username dell'utente.
    @Column(unique = true)
    private String username;

    // Campo per memorizzare l'URL dell'avatar dell'utente.
    @URL
    private String avatar;

    // Campo per memorizzare il numero telefonico dell'utente.
    @Column(unique = true)
    private String numero;

    // Campo per memorizzare la lista degli indirizzi associati all'utente.
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Indirizzo> indirizzi;

    // Campo per memorizzare la lista delle informazioni di pagamento associate all'utente.
    @Embedded
    @ManyToMany
    private List<InformazioniPagamento> informazioniPagamenti;

    // Campo per memorizzare la lista delle valutazioni effettuate dall'utente.
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Valutazione> valutazioni;

    // Campo per memorizzare la lista delle recensioni scritte dall'utente.
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Recensione> recensioni;

    // Metodo per ottenere gli elenchi dei ruoli dell'utente.
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Ruolo ruolo : ruoli) {
            authorities.add(new SimpleGrantedAuthority(ruolo.name()));
        }
        return authorities;
    }

    // Metodo per verificare se l'account dell'utente è scaduto.
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Metodo per verificare se l'account dell'utente è bloccato.
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Metodo per verificare se le credenziali dell'utente sono scadute.
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Metodo per verificare se l'account dell'utente è abilitato.
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Metodo per aggiungere un ruolo all'utente.
    public void addRuolo(Ruolo ruolo) {
        ruoli.add(ruolo);
    }
}
