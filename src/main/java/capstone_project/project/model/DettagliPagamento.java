package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "dettagli_pagamento")
@Embeddable
public class DettagliPagamento {
    @Id
    private int id;

    private String metodoPagamento;

    private String stato;
}
