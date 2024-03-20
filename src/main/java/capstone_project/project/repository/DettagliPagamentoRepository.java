package capstone_project.project.repository;

import capstone_project.project.model.DettagliPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DettagliPagamentoRepository extends JpaRepository<DettagliPagamento, Integer>, PagingAndSortingRepository<DettagliPagamento, Integer> {
}
