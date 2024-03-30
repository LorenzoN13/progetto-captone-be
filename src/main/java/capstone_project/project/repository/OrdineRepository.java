package capstone_project.project.repository;


import capstone_project.project.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Annotazione per indicare che questa interfaccia è un componente Repository gestito da Spring Data JPA.
@Repository
public interface OrdineRepository  extends JpaRepository<Ordine, Integer>, PagingAndSortingRepository<Ordine, Integer> {
    // Metodo per cercare un'ordine per ID nel database.
    Optional<Ordine> findById(int id);
}
