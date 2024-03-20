package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "informazioniPagamento")
public class InformazioniPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String NomeDelTitolare;

    @Column(name = "numero_carta", unique = true)
    private String numeroCarta;

    private LocalDate dataDiScadenza;

    private String cvv;

}
