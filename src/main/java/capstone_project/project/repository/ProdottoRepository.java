package capstone_project.project.repository;

import capstone_project.project.model.Prodotto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>, PagingAndSortingRepository<Prodotto, Integer> {

    @Query("SELECT p FROM Prodotto p" + "WHERE(P.categoria.name =: categoria OR :cagoria = '')")
    public List<Prodotto>filtraProdotti(
            @Param("categoria") Integer categoria,
            @Param("prezzoMinimo") Integer minPrezzo,
            @Param("prezzoMassimo") Integer maxPrezzo,
            @Param("prezzoSContato") Integer prezzoScontato,
            @Param("sort") String sort);
}
