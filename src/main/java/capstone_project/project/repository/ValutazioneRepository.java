package capstone_project.project.repository;

import capstone_project.project.model.Valutazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Annotazione per indicare che questa interfaccia è un componente Repository gestito da Spring Data JPA.
@Repository
public interface ValutazioneRepository extends JpaRepository<Valutazione, Integer>, PagingAndSortingRepository<Valutazione, Integer> {
    // Metodo per cercare una valutazione per ID nel database.

    Optional<Valutazione> findById(int id);

}
