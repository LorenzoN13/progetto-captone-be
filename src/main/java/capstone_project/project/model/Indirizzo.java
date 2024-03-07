package capstone_project.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "indirizzo")
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String via;
    private String civico;
    private String comune;
    private String cap;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonIgnore
    private Utente utente;
}
