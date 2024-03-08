package capstone_project.project.repository;

import capstone_project.project.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>, PagingAndSortingRepository<Prodotto, Integer> {

}
