package capstone_project.project.repository;

import capstone_project.project.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>, PagingAndSortingRepository<Prodotto, Integer> {

    // Recupera un'istanza di Prodotto in base all' id specificato
    Optional<Prodotto> findById(int id);
}
