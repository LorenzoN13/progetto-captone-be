package capstone_project.project.repository;

import capstone_project.project.model.DettagliPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
// Annotazione per indicare che questa interfaccia è un componente Repository gestito da Spring Data JPA.
@Repository
public interface DettagliPagamentoRepository extends JpaRepository<DettagliPagamento, Integer>, PagingAndSortingRepository<DettagliPagamento, Integer> {
}
