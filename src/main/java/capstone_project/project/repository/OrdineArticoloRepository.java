package capstone_project.project.repository;

import capstone_project.project.model.OrdineArticolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
// Annotazione per indicare che questa interfaccia è un componente Repository gestito da Spring Data JPA.
@Repository
public interface OrdineArticoloRepository extends JpaRepository<OrdineArticolo, Integer>, PagingAndSortingRepository<OrdineArticolo, Integer> {

    // Metodo per cercare gli articoli di un ordine dato l'ID dell'ordine.
    List<OrdineArticolo> findByOrdineId(int ordineId);
}
