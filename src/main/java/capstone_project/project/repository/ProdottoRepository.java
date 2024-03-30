package capstone_project.project.repository;

import capstone_project.project.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Annotazione per indicare che questa interfaccia è un componente Repository gestito da Spring Data JPA.
@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>, PagingAndSortingRepository<Prodotto, Integer> {
    // Metodo per cercare un prodotto per ID nel database.
    Optional<Prodotto> findById(int id);
}
