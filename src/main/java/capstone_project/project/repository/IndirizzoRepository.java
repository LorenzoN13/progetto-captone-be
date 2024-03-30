package capstone_project.project.repository;

import capstone_project.project.model.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Annotazione per indicare che questa interfaccia è un componente Repository gestito da Spring Data JPA.
@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Integer>, PagingAndSortingRepository<Indirizzo, Integer> {
    // Metodo per cercare una carta per ID nel database.
    Optional<Indirizzo> findById(int id);
}
