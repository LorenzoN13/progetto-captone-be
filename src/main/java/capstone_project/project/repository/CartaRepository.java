package capstone_project.project.repository;

import capstone_project.project.model.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Integer>, PagingAndSortingRepository<Carta, Integer> {

    Optional<Carta> findById(int id);
}
