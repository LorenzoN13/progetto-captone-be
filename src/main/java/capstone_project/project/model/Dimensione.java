package capstone_project.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "taglia")
public class Dimensione {
    private String nome;
    private int quantita;
}
