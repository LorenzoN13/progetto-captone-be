package capstone_project.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "informazioniPagamento")
public class InformazioniPagamento {

    private String NomeDelTitolare;

    @Column(name = "numero_carta", unique = true)
    private String numeroCarta;

    private LocalDate dataDiScadenza;

    private String cvv;

}
