package capstone_project.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "dettagli_pagamento")
public class DettagliPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String metodoPagamento;

    private String stato;
}
