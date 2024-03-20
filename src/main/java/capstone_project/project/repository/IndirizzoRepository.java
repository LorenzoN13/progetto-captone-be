package capstone_project.project.repository;

import capstone_project.project.model.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Integer>, PagingAndSortingRepository<Indirizzo, Integer> {

    Optional<Indirizzo> findById(int id);
}
