package capstone_project.project.repository;


import capstone_project.project.model.Valutazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValutazioneRepository extends JpaRepository<Valutazione, Integer>, PagingAndSortingRepository<Valutazione, Integer> {
    Optional<Valutazione> findById(int id);

}
