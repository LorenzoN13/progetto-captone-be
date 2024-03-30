package capstone_project.project.repository;

import capstone_project.project.model.InformazioniPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
// Annotazione per indicare che questa interfaccia è un componente Repository gestito da Spring Data JPA.
@Repository
public interface InformazioniPagamentoRepository extends JpaRepository<InformazioniPagamento, Integer>, PagingAndSortingRepository<InformazioniPagamento, Integer> {
}
