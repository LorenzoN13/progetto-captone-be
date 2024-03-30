package capstone_project.project.repository;

import capstone_project.project.model.Recensione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Annotazione per indicare che questa interfaccia è un componente Repository gestito da Spring Data JPA.
@Repository
public interface RecensioneRepository extends JpaRepository<Recensione, Integer>, PagingAndSortingRepository<Recensione, Integer> {
    // Metodo per cercare una recensione per ID nel database.
    Optional<Recensione> findById(int id);
}
