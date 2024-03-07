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

@Entity
@Data
@Table(name = "utenti")
public class Utente  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String cognome;

    @Enumerated(EnumType.STRING)
    private Set <Ruolo> ruoli;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String username;
    @URL
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



    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Ruolo ruolo : ruoli) {
            authorities.add(new SimpleGrantedAuthority(ruolo.name()));
        }
        return authorities;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addRuolo(Ruolo ruolo) {
        ruoli.add(ruolo);
    }


}
